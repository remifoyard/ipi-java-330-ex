<%@ page import="com.ipiecoles.java.java320.model.Employe" %>
<%@ page import="com.ipiecoles.java.java320.model.Commercial" %>
<%@ page import="com.ipiecoles.java.java320.model.Technicien" %>
<%@ page import="com.ipiecoles.java.java320.model.Manager" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tags/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Employe emp = (Employe) pageContext.findAttribute("employe"); %>
<div class="container">
    <h2>Détail du ${employe.className} matricule</h2>

    <div class="row">
        <form id="saveForm" action="/${employe.className.toLowerCase()}s/${employe.id}" method="post">
        <div class="col-lg-6">
            <div class="form-group">
                <label class="form-control-label" for="nom">Nom</label>
                <input type="text" value="${employe.nom} " class="form-control" name="nom" id="nom">

                <label class="form-control-label" for="prenom">Prénom</label>
                <input type="text" value="${employe.prenom}" class="form-control" name="prenom" id="prenom">

                <label class="form-control-label" for="matricule">Matricule</label>
                <input type="text" value="${employe.matricule}" class="form-control" name="matricule" id="matricule">
            </div>
        </div>
        <div class="col-lg-6">
            <div class="form-group">
                <label class="form-control-label" for="nom">Salaire</label>
                <div class="input-group">
                    <input type="number" value="${employe.salaire}" class="form-control" name="salaire" id="salaire">
                    <span class="input-group-addon">€</span>
                </div>

                <p>Si consultation employé existant on affiche la prime</p>

                <label class="form-control-label" for="nom">Prime Annuelle</label>
                <div class="input-group">
                    <input type="text" value="${employe.getPrimeAnnuelle()}" class="form-control" name="primeAnnuelle" id="primeAnnuelle">
                    <span class="input-group-addon">€</span>
                </div>


                <label class="form-control-label" for="nom">Date d'embauche</label>
                <input type="text" value="${employe.getDateEmbauche().toString('dd/MM/YYYY')}" class="form-control" name="dateEmbauche" id="dateEmbauche">

                <% if(emp instanceof Commercial) { %>
                    <p>Si l'employé est un commercial</p>
                    <label class="form-control-label" for="performance">Performance</label>
                    <input type="number" value="" class="form-control" name="performance" id="performance">

                    <label class="form-control-label" for="caAnnuel">CA Annuel</label>
                    <div class="input-group">
                        <input type="number" value="${employe.getPrimeAnnuelle()}" class="form-control" name="caAnnuel" id="caAnnuel">
                        <span class="input-group-addon">€</span>
                    </div>
                <% } %>
                <% if(emp instanceof Technicien) { %>
                    <p>Si l'employé est un technicien</p>
                    <label class="form-control-label" for="grade">Grade</label>
                    <input type="number" value="${employe.getGrade()}" class="form-control" name="grade" id="grade">
                <% } %>


                <% if(emp instanceof Manager && emp.getId() != null) { %>
                    <p>Si l'employé est un manager et que c'est une consultation</p>
                    <label class="form-control-label" for="performance">Equipe</label>
                    <div class="row">
                        <div class="col-lg-10">
                            <ul class="list-group">
                                <c:forEach var = "tech" items = "${employe.equipe}">
                                    <li class="list-group-item"><a href="/employes/${tech.id}"><c:out value = "${tech.prenom}"/> <c:out value = "${tech.nom}"/> <span class="badge pull-right"><c:out value = "${tech.matricule}"/></span></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-lg-2 text-center">
                            <div class="list-group text-center">
                                    <li class="list-group-item"><a href=""><span class="glyphicon glyphicon-remove"></span></a></li>
                            </div>
                        </div>
                    </div>
                <% } %>
            </div>
        </div>
        </form>
        <div class="col-lg-6">
            <input form="saveForm" class="btn btn-primary" type="submit" value="Enregistrer"/>
                <a href="/employes/${employe.getId()}/delete" class="btn btn-danger">Supprimer</a>
        </div>
        <div class="col-lg-6">
            <% if(emp instanceof Manager && emp.getId() != null) { %>
            <p>Si l'employé est un manager et que c'est une consultation</p>
            <form action="" method="get">
                <div class="col-lg-10">
                    <input type="text" name="matricule" value="" placeholder="Ajouter un technicien avec le matricule..." class="form-control">
                </div>
                <div class="col-lg-2 text-center">
                    <button type="submit" class="btn-success list-group-item list-group-item-action"><span class="glyphicon glyphicon-plus"></span></button>
                </div>
            </form>
            <% } %>
            <% if(emp instanceof Technicien && emp.getId() != null) { %>
            <p>Si l'employé est un technicien et que c'est une consultation</p>
                <div class="row">
                    <% if(((Technicien) emp).getManager() != null) { %>
                    <p>Si il a un manager</p>
                    <div class="col-lg-12">
                        <label class="form-control-label">Manager</label>
                    </div>
                    <div class="col-lg-10">
                        <ul class="list-group">
                            <li class="list-group-item">
                                <a href="">Prénom Nom
                                    <span class="badge pull-right">Matricule</span></a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-lg-2">
                        <li class="list-group-item"><a href=""><span class="glyphicon glyphicon-remove"></span></a></li>
                    </div>
                    <% } %>
                    <p>Sinon</p>
                    <form action="" method="get">
                    <div class="col-lg-10">
                        <input type="text" name="matricule" value="" placeholder="Ajouter un manager avec le matricule..." class="form-control">
                    </div>
                    <div class="col-lg-2 text-center">
                        <button type="submit" class="btn-success list-group-item list-group-item-action"><span class="glyphicon glyphicon-plus"></span></button>
                    </div>
                    </form>
                </div>
            <% } %>
        </div>
    </div>
</div>
<%@ include file="../tags/footer.jsp" %>