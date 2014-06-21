//script 

 function validacion() {
	
	var fecha = document.getElementById("date1").value;
	var hora = document.getElementById("date2").value;
	var nickname = document.getElementById("nick").value;
	var email = document.getElementById("email").value;
	var error = 0;
	
  if ( fecha == 'Fecha'|| fecha.length == 0 ) {
    alert('error en fecha');
    error = error + 1;
  }
  else if ( hora == 'Hora' || hora.length == 0 ) {
    alert('error en la hora');
    error = error + 1;
  }
  else if ( nickname == null || nickname.length == 0 ) {
    alert('error en el nick');
    error = error + 1;
  }
  if (error == 0){
	return true;
  }
  else {
	return false;
  }
};

 //fin mio