function validarSenhaLogin() {

	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;

	if (j_username === null || j_username.trim === '') {
		alert("Informe o Login.");
		$('j_username').focus();
		return false;
	}

	if (j_password === null || j_password.trim === '') {
		alert("Informe o Login.");
		$('j_password').focus();
		return false;
	}

	return true;
}

function validarDataAtual() {

	var dataSelecionada = document.getElementById("inicioVigencia").value;
	var dataCurrente = new Date();
	if (dataSelecionada < dataCurrente) {
		alert("A data inicio não pode ser menor que a data atual!");
		return false
	}
	return true;
}