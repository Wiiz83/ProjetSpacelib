<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello :)</title>
    </head>
    <style>
        table {
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid black;
        }
    </style>
    <body>
        <h1>Server is running !</h1>
        <table>
            <%
                try {
                    InitialContext ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/SpacelibDataSource");
                    Connection conn = ds.getConnection();
                    Statement stmt = conn.createStatement();
            %>

            
            <p>Liste des administrateurs :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de cr�ation</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Pr�nom</th>
                </tr>
                <%
                    ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINISTRATEUR");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            <p>Liste des conducteurs :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de cr�ation</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Pr�nom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM CONDUCTEUR");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des m�caniciens :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de cr�ation</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Pr�nom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM MECANICIEN");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }

                %>
            </table>
            
            
            
            <p>Liste des navettes :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Nombre de places</th>
                    <th>Nombre de voyages restants</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM NAVETTE");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getInt("NBPLACES")%></td>
                    <td><%= rs.getInt("NBVOYAGES")%></td>
                </tr>
                <%
                    }

                %>
            </table>
            

            <p>Liste des quais :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Identifiant de station</th>
                    <th>Identifiant de navette</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM QUAI");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getInt("ID_STATION")%></td>
                    <td><%= rs.getInt("ID_NAVETTE")%></td>
                </tr>
                <%
                    }
                %>
            </table>

            
            <p>Liste des r�visions :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de cr�ation</th>
                    <th>Statut</th>
                    <th>Identifiant du m�canicien</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM REVISION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                    <td><%= rs.getInt("ID_MECANICIEN")%></td>
                    <td><%= rs.getInt("ID_NAVETTE")%></td>
                    <td><%= rs.getInt("ID_QUAI")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des stations :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Localisation</th>
                    <th>Nombre de quais</th>
                    <th>Nom de station</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM STATION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("LOCALISATION")%></td>
                    <td><%= rs.getInt("NOMBRE_QUAIS")%></td>
                    <td><%= rs.getString("NOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des transferts :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date d'arriv�e</th>
                    <th>Date de cr�ation</th>
                    <th>Date de d�part</th>
                    <th>Nombre de passagers</th>
                    <th>Statut</th>
                    <th>Identifiant du conducteur</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai d'arriv�e</th>
                    <th>Identifiant du quai de d�part</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM TRANSFERT");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_ARRIVEE")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("DATE_DEPART")%></td>
                    <td><%= rs.getInt("NOMBRE_PASSAGERS")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                    <td><%= rs.getInt("ID_CONDUCTEUR")%></td>
                    <td><%= rs.getInt("ID_NAVETTE")%></td>
                    <td><%= rs.getInt("ID_QUAI_ARRIVE")%></td>
                    <td><%= rs.getInt("ID_QUAI_DEPART")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            
            <p>Liste des usagers :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de cr�ation</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Pr�nom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM USAGER");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            
            <p>Liste des voyages :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date d'arriv�e</th>
                    <th>Date de cr�ation</th>
                    <th>Date de d�part</th>
                    <th>Nombre de passagers</th>
                    <th>Statut</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai d'arriv�e</th>
                    <th>Identifiant du quai de d�part</th>
                    <th>Identifiant de l'usager</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM VOYAGE");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_ARRIVEE")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("DATE_DEPART")%></td>
                    <td><%= rs.getInt("NOMBRE_PASSAGERS")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                    <td><%= rs.getInt("ID_NAVETTE")%></td>
                    <td><%= rs.getInt("ID_QUAI_ARRIVE")%></td>
                    <td><%= rs.getInt("ID_QUAI_DEPART")%></td>
                    <td><%= rs.getInt("ID_USAGER")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            <%
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
    </body>
</html>
