using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class UsuarioMap : ClassMap<Usuario>
    {
        public UsuarioMap()
        {
            Id(x => x.Id)
                .GeneratedBy
                .Increment()
                .Not.Nullable();
            Map(x => x.Login)
              .Length(100)
              .Not.Nullable();
            Map(x => x.Senha)
              .Length(100)
              .Not.Nullable();
            Map(x => x.Nome)
              .Length(100)
              .Not.Nullable();
            Map(x => x.Sexo)
              .Length(10)
              .Not.Nullable();
            Map(x => x.Face)
              .Length(100);
            Map(x => x.Facepublic);
            Map(x => x.Wpp)
              .Length(100);
            Map(x => x.Wpppublic);
            Map(x => x.Insta)
              .Length(100);
            Map(x => x.Instapublic);
            Map(x => x.Snap)
              .Length(100);
            Map(x => x.Snappublic);
            Map(x => x.Twitter)
              .Length(100);
            Map(x => x.Twitterpublic);
            Map(x => x.Email)
              .Length(200);
            Map(x => x.Emailpublic);
            Map(x => x.Link)
              .Length(200);
            Map(x => x.Linkpublic);
            Map(x => x.Dtinscricao);
        }
    }
}