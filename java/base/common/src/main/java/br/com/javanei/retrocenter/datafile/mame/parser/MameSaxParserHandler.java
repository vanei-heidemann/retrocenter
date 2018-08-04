package br.com.javanei.retrocenter.datafile.mame.parser;

import br.com.javanei.retrocenter.common.DuplicatedItemException;
import br.com.javanei.retrocenter.common.UnknownTagException;
import br.com.javanei.retrocenter.datafile.mame.Mame;
import br.com.javanei.retrocenter.datafile.mame.MameAdjuster;
import br.com.javanei.retrocenter.datafile.mame.MameAnalog;
import br.com.javanei.retrocenter.datafile.mame.MameBiosset;
import br.com.javanei.retrocenter.datafile.mame.MameChip;
import br.com.javanei.retrocenter.datafile.mame.MameConfiguration;
import br.com.javanei.retrocenter.datafile.mame.MameConfsetting;
import br.com.javanei.retrocenter.datafile.mame.MameDevice;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceExtension;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceInstance;
import br.com.javanei.retrocenter.datafile.mame.MameDeviceref;
import br.com.javanei.retrocenter.datafile.mame.MameDipswitch;
import br.com.javanei.retrocenter.datafile.mame.MameDipvalue;
import br.com.javanei.retrocenter.datafile.mame.MameDisk;
import br.com.javanei.retrocenter.datafile.mame.MameDisplay;
import br.com.javanei.retrocenter.datafile.mame.MameDriver;
import br.com.javanei.retrocenter.datafile.mame.MameInput;
import br.com.javanei.retrocenter.datafile.mame.MameInputControl;
import br.com.javanei.retrocenter.datafile.mame.MameMachine;
import br.com.javanei.retrocenter.datafile.mame.MamePort;
import br.com.javanei.retrocenter.datafile.mame.MameRamoption;
import br.com.javanei.retrocenter.datafile.mame.MameRom;
import br.com.javanei.retrocenter.datafile.mame.MameSample;
import br.com.javanei.retrocenter.datafile.mame.MameSlot;
import br.com.javanei.retrocenter.datafile.mame.MameSlotoption;
import br.com.javanei.retrocenter.datafile.mame.MameSoftwarelist;
import br.com.javanei.retrocenter.datafile.mame.MameSound;
import br.com.javanei.retrocenter.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class MameSaxParserHandler extends DefaultHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MameSaxParserHandler.class);

    private Stack<Object> stack = new Stack<>();
    private Mame mame;
    private Object currentObj;

    private MameSaxParserHandler() {
    }

    static Mame parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MameSaxParserHandler handler = new MameSaxParserHandler();
        saxParser.parse(is, handler);
        return handler.mame;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.trim()) {
            case "mame":
                mame = new Mame();
                ReflectionUtil.setValueByAttributes(mame, attributes);
                pushToStack(mame);
                break;
            case "machine":
                MameMachine machine = new MameMachine();
                ReflectionUtil.setValueByAttributes(machine, attributes);
                mame.addMachine(machine);
                pushToStack(machine);
                if (mame.getMachines().size() % 100 == 0) {
                    LOG.info(mame.getMachines().size() + " -> " + machine.getName());
                }
                break;
            case "biosset":
                MameBiosset biosset = new MameBiosset();
                ReflectionUtil.setValueByAttributes(biosset, attributes);
                if (!((MameMachine) stack.peek()).addBiosset(biosset)) {
                    throw new DuplicatedItemException("mame.machine.biosset: " + biosset.getName() + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(biosset);
                break;
            case "rom":
                MameRom rom = new MameRom();
                ReflectionUtil.setValueByAttributes(rom, attributes);
                ((MameMachine) stack.peek()).addRom(rom);
                pushToStack(rom);
                break;
            case "disk":
                MameDisk disk = new MameDisk();
                ReflectionUtil.setValueByAttributes(disk, attributes);
                if (!((MameMachine) stack.peek()).addDisk(disk)) {
                    throw new DuplicatedItemException("mame.machine.disk: " + disk + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(disk);
                break;
            case "device_ref":
                MameDeviceref ref = new MameDeviceref();
                ReflectionUtil.setValueByAttributes(ref, attributes);
                ((MameMachine) stack.peek()).addDeviceref(ref);
                pushToStack(ref);
                break;
            case "sample":
                MameSample sample = new MameSample();
                ReflectionUtil.setValueByAttributes(sample, attributes);
                ((MameMachine) stack.peek()).addSample(sample);
                pushToStack(sample);
                break;
            case "chip":
                MameChip chip = new MameChip();
                ReflectionUtil.setValueByAttributes(chip, attributes);
                if (!((MameMachine) stack.peek()).addChip(chip)) {
                    throw new DuplicatedItemException("mame.machine.chip: " + chip + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(chip);
                break;
            case "display":
                MameDisplay display = new MameDisplay();
                ReflectionUtil.setValueByAttributes(display, attributes);
                if (!((MameMachine) stack.peek()).addDisplay(display)) {
                    throw new DuplicatedItemException("mame.machine.display: " + display + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(display);
                break;
            case "sound":
                if (((MameMachine) stack.peek()).getSound() != null) {
                    throw new DuplicatedItemException("mame.machine.sound for machine: " +
                            ((MameMachine) stack.peek()).getName());
                }
                MameSound sound = new MameSound();
                ReflectionUtil.setValueByAttributes(sound, attributes);
                ((MameMachine) stack.peek()).setSound(sound);
                pushToStack(sound);
                break;
            case "input":
                if (((MameMachine) stack.peek()).getInput() != null) {
                    throw new DuplicatedItemException("mame.machine.input for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                MameInput input = new MameInput();
                ReflectionUtil.setValueByAttributes(input, attributes);
                ((MameMachine) stack.peek()).setInput(input);
                pushToStack(input);
                break;
            case "control":
                MameInputControl control = new MameInputControl();
                ReflectionUtil.setValueByAttributes(control, attributes);
                if (!((MameInput) stack.peek()).addControl(control)) {
                    throw new DuplicatedItemException("mame.machine.input.control: " + control + " for input: "
                            + ((MameInput) stack.peek()).getService());
                }
                pushToStack(control);
                break;
            case "dipswitch":
                MameDipswitch dipswitch = new MameDipswitch();
                ReflectionUtil.setValueByAttributes(dipswitch, attributes);
                ((MameMachine) stack.peek()).addDipswitch(dipswitch);
                pushToStack(dipswitch);
                break;
            case "dipvalue":
                MameDipvalue dipvalue = new MameDipvalue();
                ReflectionUtil.setValueByAttributes(dipvalue, attributes);
                ((MameDipswitch) stack.peek()).addDipvalue(dipvalue);
                pushToStack(dipvalue);
                break;
            case "configuration":
                MameConfiguration configuration = new MameConfiguration();
                ReflectionUtil.setValueByAttributes(configuration, attributes);
                if (!((MameMachine) stack.peek()).addConfiguration(configuration)) {
                    throw new DuplicatedItemException("mame.machine.configuration: " + configuration + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(configuration);
                break;
            case "confsetting":
                MameConfsetting confsetting = new MameConfsetting();
                ReflectionUtil.setValueByAttributes(confsetting, attributes);
                if (!((MameConfiguration) stack.peek()).addConfsetting(confsetting)) {
                    throw new DuplicatedItemException("mame.machine.configuration.confsetting: " + confsetting
                            + " for configuration: " + ((MameConfiguration) stack.peek()).getName());
                }
                pushToStack(confsetting);
                break;
            case "port":
                MamePort port = new MamePort();
                ReflectionUtil.setValueByAttributes(port, attributes);
                ((MameMachine) stack.peek()).addPort(port);
                pushToStack(port);
                break;
            case "analog":
                MameAnalog analog = new MameAnalog();
                ReflectionUtil.setValueByAttributes(analog, attributes);
                ((MamePort) stack.peek()).addAnalog(analog);
                pushToStack(analog);
                break;
            case "adjuster":
                MameAdjuster adjuster = new MameAdjuster();
                ReflectionUtil.setValueByAttributes(adjuster, attributes);
                if (!((MameMachine) stack.peek()).addAdjuster(adjuster)) {
                    throw new DuplicatedItemException("mame.machine.adjuster: " + adjuster + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(adjuster);
                break;
            case "driver":
                if (((MameMachine) stack.peek()).getDriver() != null) {
                    throw new DuplicatedItemException("mame.machine.driver for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                MameDriver driver = new MameDriver();
                ReflectionUtil.setValueByAttributes(driver, attributes);
                ((MameMachine) stack.peek()).setDriver(driver);
                pushToStack(driver);
                break;
            case "device":
                MameDevice device = new MameDevice();
                ReflectionUtil.setValueByAttributes(device, attributes);
                if (!((MameMachine) stack.peek()).addDevice(device)) {
                    throw new DuplicatedItemException("mame.machine.device: " + device + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                ((MameMachine) stack.peek()).addDevice(device);
                pushToStack(device);
                break;
            case "instance":
                MameDeviceInstance instance = new MameDeviceInstance();
                ReflectionUtil.setValueByAttributes(instance, attributes);
                if (!((MameDevice) stack.peek()).addInstance(instance)) {
                    throw new DuplicatedItemException("mame.machine.device.instance: " + instance);
                }
                ((MameDevice) stack.peek()).addInstance(instance);
                pushToStack(instance);
                break;
            case "extension":
                MameDeviceExtension extension = new MameDeviceExtension();
                ReflectionUtil.setValueByAttributes(extension, attributes);
                if (!((MameDevice) stack.peek()).addExtension(extension)) {
                    throw new DuplicatedItemException("mame.machine.device.extension: " + extension);
                }
                ((MameDevice) stack.peek()).addExtension(extension);
                pushToStack(extension);
                break;
            case "slot":
                MameSlot slot = new MameSlot();
                ReflectionUtil.setValueByAttributes(slot, attributes);
                if (!((MameMachine) stack.peek()).addSlot(slot)) {
                    throw new DuplicatedItemException("mame.machine.slot: " + slot + " for machine: "
                            + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(slot);
                break;
            case "slotoption":
                MameSlotoption slotoption = new MameSlotoption();
                ReflectionUtil.setValueByAttributes(slotoption, attributes);
                if (!((MameSlot) stack.peek()).addSlotoption(slotoption)) {
                    throw new DuplicatedItemException("mame.machine.slot.slotoption: " + slotoption);
                }
                pushToStack(slotoption);
                break;
            case "softwarelist":
                MameSoftwarelist softwarelist = new MameSoftwarelist();
                ReflectionUtil.setValueByAttributes(softwarelist, attributes);
                if (!((MameMachine) stack.peek()).addSoftwarelist(softwarelist)) {
                    throw new DuplicatedItemException("mame.machine.softwarelist: " + softwarelist.getName()
                            + " for machine: " + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(softwarelist);
                break;
            case "ramoption":
                MameRamoption ramoption = new MameRamoption();
                ReflectionUtil.setValueByAttributes(ramoption, attributes);
                if (!((MameMachine) stack.peek()).addRamoption(ramoption)) {
                    throw new DuplicatedItemException("mame.machine.ramoption: " + ramoption.getContent()
                            + " for machine: " + ((MameMachine) stack.peek()).getName());
                }
                pushToStack(ramoption);
                break;
            default:
                stack.push(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Object pop = popFromStack();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = String.valueOf(ch, start, length).trim();
        Object o = stack.peek();
        if (o instanceof String) {
            String name = (String) o;
            try {
                ReflectionUtil.setValueByReflection(this.currentObj, name, value);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new UnknownTagException(name);
            }
        } else if (o instanceof MameRamoption) {
            ((MameRamoption) o).setContent(value);
        } else {
            throw new RuntimeException("TODO: Tratar: " + value);
        }
    }

    private Object pushToStack(Object obj) {
        this.currentObj = this.stack.push(obj);
        return currentObj;
    }

    private Object popFromStack() {
        Object o = stack.pop();
        if (!(o instanceof String)) {
            this.currentObj = o;
        }
        return currentObj;
    }
}
