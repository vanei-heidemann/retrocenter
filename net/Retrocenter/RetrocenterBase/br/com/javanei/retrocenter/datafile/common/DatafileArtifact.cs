using System;
using System.Collections.Generic;
using System.Text;

namespace RetrocenterBase.br.com.javanei.retrocenter.datafile.common
{
    class DatafileArtifact
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public string Year { get; set; }
        public string Comment { get; set; }
        public Dictionary<string, string> Fields { get; set; } = new Dictionary<string, string>();
        public HashSet<DatafileArtifactFile> Files { get; set; } = new HashSet<DatafileArtifactFile>();
        public HashSet<Release> Releases { get; set; } = new HashSet<Release>();

        public DatafileArtifact()
        {
        }

        public DatafileArtifact(string name, string description, string year, string comment)
        {
            Name = name;
            Description = description;
            Year = year;
            Comment = comment;
        }

        public DatafileArtifact(string name, string description, string year, string comment, Dictionary<string, string> fields)
        {
            Name = name;
            Description = description;
            Year = year;
            Comment = comment;
            Fields = fields;
        }

        public bool AddFile(DatafileArtifactFile file)
        {
            return Files.Add(file);
        }

        public bool AddRelease(Release release)
        {
            return Releases.Add(release);
        }

        public void AddField(string key, string value)
        {
            if (Fields == null)
            {
                Fields = new Dictionary<string, string>();
            }
            Fields.Add(key, value);
        }

        public string GetField(string key)
        {
            return Fields[key];
        }

        public string GetIsbios()
        {
            return Fields["isbios"];
        }

        public void SetIsbios(string isbios)
        {
            if (isbios != null)
            {
                Fields.Add("isbios", isbios);
            }
            else
            {
                Fields.Remove("isbios");
            }
        }

        public string GetManufacturer()
        {
            return Fields["manufacturer"];
        }

        public void SetManufacturer(string manufacturer)
        {
            if (manufacturer != null)
            {
                Fields.Add("manufacturer", manufacturer);
            }
            else
            {
                Fields.Remove("manufacturer");
            }
        }

        public string GetCloneof()
        {
            return Fields["cloneof"];
        }

        public void SetCloneof(string cloneof)
        {
            if (cloneof != null)
            {
                Fields.Add("cloneof", cloneof);
            }
            else
            {
                Fields.Remove("cloneof");
            }
        }

        public string GetRomof()
        {
            return Fields["romof"];
        }

        public void SetRomof(string romof)
        {
            if (romof != null)
            {
                Fields.Add("romof", romof);
            }
            else
            {
                Fields.Remove("romof");
            }
        }

        public string GetSampleof()
        {
            return Fields["sampleof"];
        }

        public void SetSampleof(string sampleof)
        {
            if (sampleof != null)
            {
                Fields.Add("sampleof", sampleof);
            }
            else
            {
                Fields.Remove("sampleof");
            }
        }

        public override bool Equals(object obj)
        {
            var artifact = obj as DatafileArtifact;
            return artifact != null &&
                   Name == artifact.Name;
        }

        public override int GetHashCode()
        {
            return 539060726 + EqualityComparer<string>.Default.GetHashCode(Name);
        }

        public override string ToString()
        {
            return "DatafileArtifact{" +
                   "name='" + Name + '\'' +
                   ", description='" + Description + '\'' +
                   ", year='" + Year + '\'' +
                   ", comment='" + Comment + '\'' +
                   '}';
        }
    }
}
