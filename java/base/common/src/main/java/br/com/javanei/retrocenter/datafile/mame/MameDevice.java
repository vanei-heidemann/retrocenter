package br.com.javanei.retrocenter.datafile.mame;

import br.com.javanei.retrocenter.util.StringUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MameDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String tag;
    private Integer fixed_image;
    private Integer mandatory;
    private String _interface;

    private Set<MameDeviceInstance> instances = new HashSet<>();
    private Set<MameDeviceExtension> extensions = new HashSet<>();

    public MameDevice(String type, String tag, Integer fixed_image, Integer mandatory, String _interface) {
        this.type = type;
        this.tag = tag;
        this.fixed_image = fixed_image;
        this.mandatory = mandatory;
        this._interface = _interface;
    }

    public MameDevice() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getFixed_image() {
        return fixed_image;
    }

    public void setFixed_image(Integer fixed_image) {
        this.fixed_image = fixed_image;
    }

    public void setFixed_image(String fixed_image) {
        if (fixed_image != null)
            this.fixed_image = new Integer(fixed_image);
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public void setMandatory(String mandatory) {
        if (mandatory != null)
            this.mandatory = new Integer(mandatory);
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public Set<MameDeviceInstance> getInstances() {
        return instances;
    }

    public void setInstances(Set<MameDeviceInstance> instances) {
        this.instances = instances;
    }

    public boolean addInstance(MameDeviceInstance instance) {
        return this.instances.add(instance);
    }

    public Set<MameDeviceExtension> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<MameDeviceExtension> extensions) {
        this.extensions = extensions;
    }

    public boolean addExtension(MameDeviceExtension extension) {
        return this.extensions.add(extension);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MameDevice that = (MameDevice) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, tag);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<device");
        if (this.type != null) {
            sb.append(" type=\"").append(this.type).append("\"");
        }
        if (this.tag != null) {
            sb.append(" tag=\"").append(this.tag).append("\"");
        }
        if (this.fixed_image != null) {
            sb.append(" fixed_image=\"").append(this.fixed_image).append("\"");
        }
        if (this.mandatory != null) {
            sb.append(" mandatory=\"").append(this.mandatory).append("\"");
        }
        if (this._interface != null) {
            sb.append(" interface=\"").append(this._interface).append("\"");
        }
        sb.append(">").append(StringUtil.LINE_SEPARATOR);

        for (MameDeviceInstance instance : this.instances) {
            sb.append(instance.toString());
        }
        for (MameDeviceExtension extension : this.extensions) {
            sb.append(extension.toString());
        }

        sb.append("\t\t</device>").append(StringUtil.LINE_SEPARATOR);
        return sb.toString();
    }
}
