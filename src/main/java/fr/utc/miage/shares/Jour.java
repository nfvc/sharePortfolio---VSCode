/*
 * Copyright 2025 David Navarre <David.Navarre@irit.fr>.
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
 * This class aims at describing a day based on a year and the day in this year.
 *
 * @author David Navarre &lt;David.Navarre@ut-capitole.fr&gt;
 */
public class Jour {

    /**
     * Attribut Année.
     */
    private final int year;
    /**
     * Attribut Jour.
     */
    private final int day;

    /**
     * Construit un objet Jour à partir d'une année et d'un jour.
     *
     * @param aYear l'année du jour &gt; 0
     * @param aDay le jour &gt; 0
     */
    public Jour(final int aYear, final int aDay) {
        if (0 >= aDay) {
            throw new IllegalArgumentException("Le jour doit être strictement supérieur à 0");
        }
        if (0 >= aYear) {
            throw new IllegalArgumentException("L'année doit être strictement supérieure à 0");
        }
        this.year = aYear;
        this.day = aDay;
    }

    /**
     * Retourne l'année du jour.
     *
     * @return la valeur de l'année
     */
    public int getYear() {
        return year;
    }

    /**
     * Retourne le jour.
     *
     * @return la valeur du jour
     */
    public int getDay() {
        return day;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + year;
        result = prime * result + day;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Jour other = (Jour) obj;
        return (year == other.year) && (day == other.day);
    }

    @Override
    public String toString() {
        return "Jour [année=" + year + ", jour=" + day + "]";
    }

}
