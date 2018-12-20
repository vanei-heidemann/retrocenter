using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.datafile.common
{
    class DatafileArtifactFile
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public string Size { get; set; }
        public string Crc { get; set; }
        public string Sha1 { get; set; }
        public string Md5 { get; set; }
        public string Date { get; set; }
        public Dictionary<string, string> Fields { get; set; } = new Dictionary<string, string>();

        public DatafileArtifactFile()
        {
        }

        public DatafileArtifactFile(string type)
        {
            Type = type;
        }

        public DatafileArtifactFile(string type, string name)
        {
            Type = type;
            Name = name;
        }

        public DatafileArtifactFile(string type, string name, string size, string crc, string sha1, string md5, string date)
        {
            Type = type;
            Name = name;
            Size = size;
            Crc = crc;
            Sha1 = sha1;
            Md5 = md5;
            Date = date;
        }

        public DatafileArtifactFile(string type, string name, string size, string crc, string sha1, string md5, string date,
            Dictionary<string, string> fields)
        {
            Type = type;
            Name = name;
            Size = size;
            Crc = crc;
            Sha1 = sha1;
            Md5 = md5;
            Date = date;
            Fields = fields;
        }

        public void SetField(string key, string value)
        {
            if (value != null)
            {
                Fields.Add(key, value);
            }
            else
            {
                Fields.Remove(key);
            }
        }

        public string GetField(string key)
        {
            return Fields[key];
        }


        public string GetStatus()
        {
            return Fields["status"];
        }

        public void SetStatus(string status)
        {
            if (status != null)
            {
                Fields.Add("status", status);
            }
            else
            {
                Fields.Remove("status");
            }
        }

        public string GetMerge()
        {
            return Fields["merge"];
        }

        public void SetMerge(string merge)
        {
            if (merge != null)
            {
                Fields.Add("merge", merge);
            }
            else
            {
                Fields.Remove("merge");
            }
        }

        public string GetRegion()
        {
            return Fields["region"];
        }

        public void SetRegion(string region)
        {
            if (region != null)
            {
                Fields.Add("region", region);
            }
            else
            {
                Fields.Remove("region");
            }
        }

        public override bool Equals(object obj)
        {
            var file = obj as DatafileArtifactFile;
            return file != null &&
                   Type == file.Type &&
                   Name == file.Name &&
                   Size == file.Size &&
                   Crc == file.Crc &&
                   Sha1 == file.Sha1 &&
                   Md5 == file.Md5 &&
                   Date == file.Date &&
                   EqualityComparer<Dictionary<string, string>>.Default.Equals(Fields, file.Fields);
        }

        public override int GetHashCode()
        {
            var hashCode = -1500167628;
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Type);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Name);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Size);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Crc);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Sha1);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Md5);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Date);
            hashCode = hashCode * -1521134295 + EqualityComparer<Dictionary<string, string>>.Default.GetHashCode(Fields);
            return hashCode;
        }
    }
}
