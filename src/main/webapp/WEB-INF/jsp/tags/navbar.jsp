<%@ page contentType="text/html; charset=UTF-8" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">Gestion des employés</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/employes?page=0&size=10&sortProperty=matricule&sortDirection=ASC" class="nav-link">Liste des employés <span class="badge">${nbEmployes}</span></a></li>
            </ul>

            <form class="navbar-form navbar-right" role="search" action="/" method="GET">
                <div class="form-group">
                    <input name="matricule" class="form-control" placeholder="Rechercher par matricule" type="text">
                </div>
                <button type="submit" class="btn btn-default">Rechercher</button>
            </form>
        </div>
    </div>
</nav>