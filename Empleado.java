
/**
 *
 * @author Oswaldo
 */
public class Empleado {

    private String codigo;
    private String nombres;
    private float numero_horas;
    private float valor_hora;
    private float porcentaje;


    public Empleado(String codigo, String nombres, float numero_horas, float valor_hora, float porcentaje) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.numero_horas = numero_horas;
        this.valor_hora = valor_hora;
        this.porcentaje = porcentaje;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public float getNumHoras() {
        return numero_horas;
    }

    public float getValorHora() {
        return valor_hora;
    }

    public float getPorcentaje() {
        return porcentaje;
    }
    public float getSalarioBruto() {
        return valor_hora*numero_horas;
    }

    public float getSalarioNeto() {
        float salario_neto = getSalarioBruto()*(1-porcentaje/100);
        return salario_neto;
    }
    @Override
    public String toString() {
        return "Codigo: " + codigo +"\nNombres: " + nombres+"\nSalario Bruto: "+getSalarioBruto() + "\nSalario Neto: "+getSalarioNeto();
    }

}