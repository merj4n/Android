package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

public class Poblacion {
    private String provincia;
    private String localidad;
    private float valoracion;
    private String comentarios;

    public Poblacion(String provincia, String localidad, float valoracion, String comentarios) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.valoracion = valoracion;
        this.comentarios = comentarios;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poblacion poblacion = (Poblacion) o;

        if (!provincia.equals(poblacion.provincia)) return false;
        return localidad.equals(poblacion.localidad);
    }

    @Override
    public int hashCode() {
        int result = provincia.hashCode();
        result = 31 * result + localidad.hashCode();
        return result;
    }
}
