// This file is required by app.js. It sets up event listeners
// for the two main URL endpoints of the application - /create and /chat/:id
// and listens for socket.io messages.

// Use the gravatar module, to turn email addresses into avatar images:

var gravatar = require('gravatar');
var fs = require('fs');

// Export a function, so that we can pass 
// the app and io instances from the app.js file:

module.exports = function(app,io){

//	app.get('/', function(req, res){
//
//		// Render views/home.html
//		res.render('home');
//	});
//
//	app.get('/create', function(req,res){
//
//		// Generate unique id for the room
//		var id = Math.round((Math.random() * 1000000));
//
//		// Redirect to the random room
//		res.redirect('/chat/'+id);
//	});

	app.get('/:serviceId/:userMeId/:userOtherId', function(req,res){

        var serviceId = req.param("serviceId");
        userMeId = req.param("userMeId");
        userOtherId = req.param("userOtherId");

        fs.readFile(__dirname + '/public/data/service_'+serviceId+'.json', 'utf8', function (err, service) {

            if (err) {
                console.log('Error: ' + err);
                return;
            }

            service = JSON.parse(service);


            fs.readFile(__dirname + '/public/data/user_'+userMeId+'.json', 'utf8', function (err, userMe) {

                if (err) {
                    console.log('Error: ' + err);
                    return;
                }

                userMe = JSON.parse(userMe);

                fs.readFile(__dirname + '/public/data/user_'+ userOtherId+'.json', 'utf8', function (err, userOther) {

                    if (err) {
                        console.log('Error: ' + err);
                        return;
                    }

                    userOther = JSON.parse(userOther);

                    var MongoClient = require('mongodb').MongoClient;

                    MongoClient.connect('mongodb://localhost:27017', function(err, db) {
                        if(err) throw err;

                        db.collection('chat').find().toArray( function(err, history) {

                            // Render the chant.html view
                            res.render('chat', { service: service, userMe: userMe, userOther: userOther, history: history });

                            db.close();

                        });
                    });


                });


            });
        });

	});

	// Initialize a new socket.io application, named 'chat'
	var chat = io.of('/socket').on('connection', function (socket) {

		// When the client emits the 'load' event, reply with the 
		// number of people in this chat room

		socket.on('load',function(data){

            var peopleInRoom = chat.clients(data).length
            if(chat.clients(data).length === 0 ) {

				socket.emit('peopleinchat', {number: 0});
			}
			else if(chat.clients(data).length === 1) {

				socket.emit('peopleinchat', {
					number: 1,
					user: chat.clients(data)[0].username,
					avatar: chat.clients(data)[0].avatar,
					id: data
				});
			}
			else if(chat.clients(data).length >= 2) {

				chat.emit('tooMany', {boolean: true});
			}
		});

		// When the client emits 'login', save his name and avatar,
		// and add them to the room
		socket.on('login', function(data) {

			// Only two people per room are allowed
			if(chat.clients(data.id).length < 2){

				// Use the socket object to store data. Each client gets
				// their own unique socket object

				socket.username = data.user;
				socket.room = data.id;
				socket.avatar = gravatar.url(data.avatar, {s: '140', r: 'x', d: 'mm'});

				// Tell the person what he should use for an avatar
				socket.emit('img', socket.avatar);


				// Add the client to the room
				socket.join(data.id);

				if(chat.clients(data.id).length == 2) {

					var usernames = [],
						avatars = [];

					usernames.push(chat.clients(data.id)[0].username);
					usernames.push(chat.clients(data.id)[1].username);

					avatars.push(chat.clients(data.id)[0].avatar);
					avatars.push(chat.clients(data.id)[1].avatar);

					// Send the startChat event to all the people in the
					// room, along with a list of people that are in it.

					chat.in(data.id).emit('startChat', {
						boolean: true,
						id: data.id,
						users: usernames,
						avatars: avatars
					});
				}

			}
			else {
				socket.emit('tooMany', {boolean: true});
			}
		});

		// Somebody left the chat
		socket.on('disconnect', function() {

			// Notify the other person in the chat room
			// that his partner has left

			socket.broadcast.to(this.room).emit('leave', {
				boolean: true,
				room: this.room,
				user: this.username,
				avatar: this.avatar
			});

			// leave the room
			socket.leave(socket.room);
		});


		// Handle the sending of messages
		socket.on('msg', function(data){
            var MongoClient = require('mongodb').MongoClient;

            console.log(data);

            MongoClient.connect('mongodb://localhost:27017', function(err, db) {
                if(err) throw err;

                var document = {service: data.service, serviceId: data.service.id, user: data.user, img: data.img, userMe: data.userMe, userOther: data.userOther, msg: data.msg, time: new Date().getTime() };

                db.collection('chat').insert(document, function(err, docs) {

                    db.close();
                    // When the server receives a message, it sends it to the other person in the room.
                    socket.broadcast.to(socket.room).emit('receive', {msg: data.msg, user: data.user, img: data.img});
                });
            });

		});
	});
};



