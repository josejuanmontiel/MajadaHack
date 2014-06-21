//script 

 function validacion() {
	
	var fecha = document.getElementById("date1").value;
	var hora = document.getElementById("date2").value;
	var categoria = document.getElementById("categoria").value;
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
  else if ( !(/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)/.test(email))){
	alert('error en el email');
    error = error + 1;
  }
 
  // Si el script ha llegado a este punto, todas las condiciones
  // se han cumplido, por lo que se devuelve el valor true
  if (error == 0){
	return true;
  }
  else {
	return false;
  }
};

 //fin mio