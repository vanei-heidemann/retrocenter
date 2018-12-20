using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.datafile.common
{
    interface DatafileObject
    {
        Datafile ToDatafile();

        string toFile();
    }
}
