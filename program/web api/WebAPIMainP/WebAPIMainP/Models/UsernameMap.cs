using FluentNHibernate.Mapping;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebAPIMainP.Models
{
    public class UsernameMap : ClassMap<Username>
    {
        public UsernameMap()
        {
            Table("Username");
            Id(x => x.Id).GeneratedBy.Increment().Not.Nullable();
            References(x => x.Idusuario, "Idusuario").Not.LazyLoad();
            References(x => x.Idredesocial, "Idredesocial").Not.LazyLoad();
            Map(x => x.Nomeusuario).Length(300);
            Map(x => x.Privado);
        }
    }
}