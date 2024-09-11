package edu.unisabana.dyas.tdd.registry;

import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {
    private Registry registry = new Registry();
    // @Test
    // public void validateRegistryResult() {
    // Person person = new Person();
    // RegisterResult result = registry.registerVoter(person);
    // Assert.assertEquals(RegisterResult.VALID, result);
    // }
    // TODO Complete with more test cases

    /*
     * Clase de equivalencia #1
     * Validar que la persona tenga más de 18 años, por lo que va haber un caso
     * valido y otro invalido
     */

    @Test
    public void validarEdadBajoRango() {
        Person underagePerson = new Person("John", 123456, 16, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(underagePerson);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void validarEdadCumplida() {
        Person validPerson = new Person("Carlos", 126, 20, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    /*
     * Clase de equivalencia #2
     * Validar si la persona esta viva, por lo que va haber un caso valido y otro
     * invalido
     */

    @Test
    public void validarVida() {
        Person validPerson = new Person("Maria", 12392, 19, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void validarMuerte() {
        Person validPerson = new Person("Alberto", 6, 99, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    /*
     * Clase de equivalencia #3
     * Validar si la edad se encuentra dentro del rango permitido (1 a 100 años).
     * Por lo que va haber un caso valido y dos invalidos
     */

    @Test
    public void validarEdadNegativa() {
        Person validPerson = new Person("Edwin", 2323, -5, Gender.UNIDENTIFIED, true);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validarEdadFueraRango() {
        Person validPerson = new Person("Samuel", 1230, 500, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validarEdadEnRango() {
        Person validPerson = new Person("Carolina", 1530, 23, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(validPerson);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    /*
     * Clase de equivalencia #4
     * Validar si esta duplicada la persona (basandonos en el id).
     * Por lo que va haber un caso valido (mismos datos pero diferente id) y uno
     * invalido (mismo id)
     */

    @Test
    public void validarPersonaDuplicada() {
        Person persona1 = new Person("Bob", 123123, 25, Gender.MALE, true);
        Person persona2 = new Person("Bob", 123123, 25, Gender.MALE, true);
        registry.registerVoter(persona1);
        RegisterResult result = registry.registerVoter(persona2);
        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

    @Test
    public void validarPersonaNoDuplicada() {
        Person persona1 = new Person("Richard", 1239, 22, Gender.MALE, true);
        Person persona2 = new Person("Ricahr", 13242, 22, Gender.MALE, true);
        registry.registerVoter(persona1);
        RegisterResult result = registry.registerVoter(persona2);
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    /*
     * Clase de equivalencia #5
     * Validar que el votante sea valido, por lo que no se debe repetir,DEBE estar
     * vivo y cumplir la
     * edad, por lo que solo se espera de RegisterResult se ha valid
     */

    @Test
    public void validarVotanteValido() {
        Person votante = new Person("Nicolas", 2776, 19, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(votante);
        Assert.assertNotSame(RegisterResult.INVALID_AGE, result);
    }

}