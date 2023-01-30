/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuari
 */
@Entity
public class ClassePerProvarTipusDades implements Serializable {

    @Id
    Integer campInteger;
    Byte campByte;
    Short campShort;
    Long campLong;
    Float campFloat;
    Double campDouble;
    BigInteger campBigInteger;
    BigDecimal campBigDecimal;
    Boolean campBoolean;
    Character campCharacter;
    String campString;

    public ClassePerProvarTipusDades() {
    }

}
