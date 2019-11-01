function getURL() {
	var url = window.location;
	url = url.protocol+"//"+url.host;
	return url;
}




function deleteCategorie(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/categories/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}

function deleteEmprunt(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/emprunts/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}

function deleteLigneEmprunt(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/categories/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}

function deleteExemplaire(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/exemplaires/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}

function deleteLivre(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/livres/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}

function deleteUtilisateur(id){
	var catData = {
			id : id
		}
		$.ajax({
			url : getURL() + "/api/utilisateurs/"+ id,
			contentType : "application/json",
			type : 'DELETE',
			data : JSON.stringify(catData),
			success : function(result) {
				/*if (result.status) {
					
				} else {
					
				}*/
				setTimeout("window.location.reload(true)", 1000);
			},
			error : function(e) {
				
			}
		});
}