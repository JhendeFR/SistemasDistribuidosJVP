using System;
using System.Web.Services;
using MySql.Data.MySqlClient;
using System.Configuration;

[WebService(Namespace = "http://tempuri.org/")]
public class WebService : System.Web.Services.WebService
{
    string conexion;

    public WebService()
    {
        var cs = ConfigurationManager.ConnectionStrings["conexionDB"];
        if (cs != null)
            conexion = cs.ConnectionString;
        else
            conexion = null; // will be handled in methods to avoid NullReferenceException
    }

    [WebMethod]
    public string obtenerCotizacion(string fecha)
    {
        if (string.IsNullOrEmpty(conexion))
        {
            return "Error: cadena de conexión 'conexionDB' no configurada.";
        }
        DateTime f;
        if (!DateTime.TryParseExact(fecha, "dd-MM-yy", null, 
            System.Globalization.DateTimeStyles.None, out f))
        {
            return "Fecha inválida";
        }

        string fechaMysql = f.ToString("yyyy-MM-dd");

        using (MySqlConnection conn = new MySqlConnection(conexion))
        {
            conn.Open();
            string query = "SELECT cotizacion, cotizacion_oficial FROM cotizaciones WHERE fecha=@fecha";

            MySqlCommand cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@fecha", fechaMysql);

            var reader = cmd.ExecuteReader();

            if (reader.Read())
            {
                return $"Cotización: {reader["cotizacion"]}, Oficial: {reader["cotizacion_oficial"]}";
            }
            else
            {
                return "No existe esa fecha";
            }
        }
    }

    [WebMethod]
    public string registrarCotizacion(string fecha, double monto)
    {
        if (string.IsNullOrEmpty(conexion))
        {
            return "Error: cadena de conexión 'conexionDB' no configurada.";
        }
        DateTime f;
        if (!DateTime.TryParseExact(fecha, "dd-MM-yy", null,
            System.Globalization.DateTimeStyles.None, out f))
        {
            return "Fecha inválida";
        }

        string fechaMysql = f.ToString("yyyy-MM-dd");

        using (MySqlConnection conn = new MySqlConnection(conexion))
        {
            conn.Open();
            string query = "INSERT INTO cotizaciones(fecha, cotizacion, cotizacion_oficial) VALUES(@fecha,@monto,6.97)";

            MySqlCommand cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@fecha", fechaMysql);
            cmd.Parameters.AddWithValue("@monto", monto);

            try
            {
                cmd.ExecuteNonQuery();
                return "Registrado correctamente";
            }
            catch (Exception e)
            {
                return "Error: " + e.Message;
            }
        }
    }
}
