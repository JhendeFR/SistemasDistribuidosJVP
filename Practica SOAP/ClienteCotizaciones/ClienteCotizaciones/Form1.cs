// Verifica que este nombre coincida con tu carpeta de Connected Services
using ClienteCotizaciones.ServiceCotizacion;
using ServiceCotizacion;
using System;
using System.Windows.Forms;

namespace ClienteCotizaciones
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private async void btnConsultar_Click(object sender, EventArgs e)
        {
            try
            {
                // Pasamos "WebServiceSoap" como argumento si pide uno
                using (var client = new WebServiceSoapClient("WebServiceSoap"))
                {
                    var respuesta = await client.obtenerCotizacionAsync(txtFecha.Text);
                    lblResultado.Text = respuesta.Body.obtenerCotizacionResult;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }

        private async void btnRegistrar_Click(object sender, EventArgs e)
        {
            try
            {
                using (var client = new WebServiceSoapClient("WebServiceSoap"))
                {
                    double monto = double.Parse(txtMonto.Text);
                    var respuesta = await client.registrarCotizacionAsync(txtFecha.Text, monto);
                    MessageBox.Show(respuesta.Body.registrarCotizacionResult);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }
    }
}