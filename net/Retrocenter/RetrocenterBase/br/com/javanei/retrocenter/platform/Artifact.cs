using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.platform
{
    class Artifact
    {
        public string Code { get; set; }
        public string Name { get; set; }

        public Artifact()
        {
        }

        public Artifact(string code, string name)
        {
            this.Code = code;
            this.Name = name;
        }

        public override string ToString()
        {
            return "Artifact{" +
                   "code='" + Code + '\'' +
                   ", name='" + Name + '\'' +
                   '}';
        }
    }
}
