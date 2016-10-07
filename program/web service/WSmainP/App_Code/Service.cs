using MySql.Data.MySqlClient;
using System.Web.Script.Serialization;
using System.Web.Script.Services;
using System.Web.Services;

[WebService(Namespace = "http://nsmainp.com/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]

public class Service : System.Web.Services.WebService
{
    public Service () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
        
    }

    [WebMethod]
    [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
    public void CarregarPerfil()
    {
        User user = new User();

        //conexão com o bd 
        MySqlConnection Conn = new MySqlConnection("Server=localhost;Database=NSmainP;UID=root;Password=1234");

        //select no bd
        MySqlCommand cmd = new MySqlCommand("SELECT * FROM USER WHERE SEXO='F'", Conn);

        Conn.Open();

        //To Read From SQL Server
        MySqlDataReader dr = cmd.ExecuteReader();

        if (dr.Read())
        {
            user.ID = dr["ID"].ToString();
            user.LOGIN = dr["LOGIN"].ToString();
            user.SENHA = dr["SENHA"].ToString();
            user.NOME = dr["NOME"].ToString();
            user.SEXO = dr["SEXO"].ToString();
            user.FACE = dr["FACE"].ToString();
            user.FACEpublic = dr["FACEpublic"].ToString();
            user.WPP = dr["WPP"].ToString();
            user.WPPpublic = dr["WPPpublic"].ToString();
            user.INSTA = dr["INSTA"].ToString();
            user.INSTApublic = dr["INSTApublic"].ToString();
            user.SNAP = dr["SNAP"].ToString();
            user.SNAPpublic = dr["SNAPpublic"].ToString();
            user.TWITTER = dr["TWITTER"].ToString();
            user.TWITTERpublic = dr["TWITTERpublic"].ToString();
            user.EMAIL = dr["EMAIL"].ToString();
            user.EMAILpublic = dr["EMAILpublic"].ToString();
            user.LINK = dr["LINK"].ToString();
            user.LINKpublic = dr["LINKpublic"].ToString();
            user.DTINSCRICAO = dr["DTINSCRICAO"].ToString();
        }

        dr.Close();
        Conn.Close();
        
        JavaScriptSerializer jsslz = new JavaScriptSerializer();

        string json = string.Empty;

        json = jsslz.Serialize(user);

        Context.Response.Output.Write(json);
    }   
}