<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>APPJEE2024</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  	<div class="container-fluid" style="margin-top: 100px">
  		<h3 style="text-align: center">FORMULAIRE D'AJOUT DE PRODUITS</h3>
  		<br>
  		<div class="row">
	  		<div class="col-md-3"></div>
	  		<div class="col-md-6">
	  			<form class="row g-3" method="post" action="<%=request.getContextPath()%>/ajouter-produit">
			  		<div class="form-floating mb-3">
					  <input name="designation" type="text" class="form-control" id="floatingInput" placeholder="Désignation">
					  <label for="floatingInput">Désignation</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="prix" type="number" class="form-control" id="floatingInput" placeholder="Prix">
					  <label for="floatingInput">Prix</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="quantite" type="number" class="form-control" id="floatingInput" placeholder="Quantité">
					  <label for="floatingInput">Quantité</label>
					</div>
				    <button type="submit" class="btn btn-primary mb-3">Ajouter</button>
				</form>
	  		</div>
	  		<div class="col-md-3"></div>
  		</div>
  	</div>
  </body>
</html>