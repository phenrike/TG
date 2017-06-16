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
            Table("Usuario");
            Id(x => x.Id).GeneratedBy.Increment().Not.Nullable();
            Map(x => x.Login).Length(100).Not.Nullable();
            Map(x => x.Senha).Length(100).Not.Nullable();
            Map(x => x.Nome).Length(100).Not.Nullable();
            Map(x => x.Sexo).Length(10);
            Map(x => x.Dtinscricao);
            HasMany(x => x.Usernames).KeyColumn("IDUSUARIO").Inverse().Cascade.All().Not.LazyLoad();
        }
    }
}