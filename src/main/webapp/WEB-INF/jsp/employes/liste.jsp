<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tags/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1>Liste des employés</h1>
            <div class="btn-group">
                <a href="#" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    Nouvel employé
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/employes/new/technicien">Technicien</a></li>
                    <li><a href="/employes/new/commercial">Commercial</a></li>
                    <li><a href="/employes/new/manager">Manager</a></li>
                </ul>
            </div>
            <c:set var = "inverseDirection" scope = "page" value = "${sortDirection == 'DESC' ? 'ASC':'DESC'}"/>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">
                        <a href="employes?page=${page}&size=${size}&sortProperty=matricule&sortDirection=${inverseDirection}">Matricule
                            <c:if test = "${sortProperty == 'matricule' && sortDirection == 'DESC'}">
                                <span class="glyphicon glyphicon-chevron-up"></span>
                            </c:if>
                            <c:if test = "${sortProperty == 'matricule' && sortDirection == 'ASC'}">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="employes?page=${page}&size=${size}&sortProperty=nom&sortDirection=${inverseDirection}">Nom
                            <c:if test = "${sortProperty == 'nom' && sortDirection == 'DESC'}">
                                <span class="glyphicon glyphicon-chevron-up"></span>
                            </c:if>
                            <c:if test = "${sortProperty == 'nom' && sortDirection == 'ASC'}">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </c:if>
                        </a>
                    </th>
                    <th scope="col">
                        <a href="employes?page=${page}&size=${size}&sortProperty=prenom&sortDirection=${inverseDirection}">Prénom
                            <c:if test = "${sortProperty == 'prenom' && sortDirection == 'DESC'}">
                                <span class="glyphicon glyphicon-chevron-up"></span>
                            </c:if>
                            <c:if test = "${sortProperty == 'prenom' && sortDirection == 'ASC'}">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </c:if>
                        </a>
                    </th>
                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var = "emp" items = "${employes.getContent()}">
                        <tr>
                            <th scope="row"><c:out value = "${emp.matricule}"/></th>
                            <td><c:out value = "${emp.nom}"/></td>
                            <td><c:out value = "${emp.prenom}"/></td>
                            <td><a href="employes/${emp.id}">Détail</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="row">
                <div class="col-lg-6">
                    <p>
                        Affichage des employés ${start} à ${end} sur un total de ${totalElem}</p>
                </div>
                <div class="col-lg-6">
                    <ul class="pagination">
                        <c:if test = "${page > 0}">
                            <li><a href="employes?page=${page - 1}&size=${size}&sortProperty=${sortProperty}&sortDirection=${sortDirection}">&laquo;</a></li>
                        </c:if>
                        <li><a href="#">Page ${page + 1}</a></li>
                        <c:if test = "${page<totalPages-1}">
                            <li><a href="employes?page=${page + 1}&size=${size}&sortProperty=${sortProperty}&sortDirection=${sortDirection}">&raquo;</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../tags/footer.jsp" %>
