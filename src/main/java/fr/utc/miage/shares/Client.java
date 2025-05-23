/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utc.miage.shares;

/**
 * Représente un client du système boursier.
 */
public class Client {

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String mail;
    private String password;


    /**
     * Constructeur de la classe Client.
     * Initialise un nouveau client avec ses informations personnelles.
     *
     * @param nom Le nom du client
     * @param prenom Le prénom du client
     * @param adresse L'adresse postale du client
     * @param mail L'adresse e-mail du client
     * @param telephone Le numéro de téléphone du client
     * @param password Le mot de passe du client
     */
    public Client(String nom, String prenom, String adresse, String mail, String telephone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.telephone = telephone;
        this.password = password;
    }

    /**
     * GETTER
     *
     */

    /**
     * Récupère le nom du client.
     *
     * @return Le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom du client.
     *
     * @return Le prénom du client
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère l'adresse du client.
     *
     * @return L'adresse postale du client
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie le nom du client.
     *
     * @param nom Le nouveau nom à attribuer au client
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le numéro de téléphone du client.
     *
     * @return Le numéro de téléphone du client
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Récupère l'adresse e-mail du client.
     *
     * @return L'adresse e-mail du client
     */
    public String getMail() {
        return mail;
    }

    /**
     * Récupère le mot de passe du client.
     *
     * @return Le mot de passe du client
     */
    public String getPassword() {
        return password;
    }

    /**
     * SETTER
     */

    /**
     * Modifie le prénom du client.
     *
     * @param prenom Le nouveau prénom à attribuer au client
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Modifie l'adresse du client.
     *
     * @param adresse La nouvelle adresse à attribuer au client
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Modifie le numéro de téléphone du client.
     *
     * @param telephone Le nouveau numéro de téléphone à attribuer au client
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Modifie l'adresse e-mail du client.
     *
     * @param mail La nouvelle adresse e-mail à attribuer au client
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Modifie le mot de passe du client.
     *
     * @param password Le nouveau mot de passe à attribuer au client
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
