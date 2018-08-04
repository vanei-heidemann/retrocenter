package br.com.javanei.retrocenter.datafile.mame.service;

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
import br.com.javanei.retrocenter.datafile.mame.MameRom;
import br.com.javanei.retrocenter.datafile.mame.MameSample;
import br.com.javanei.retrocenter.datafile.mame.MameSound;
import br.com.javanei.retrocenter.datafile.mame.parser.MameParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MameServiceConfiguration.class})
public class MameServiceTest {
    private static Mame datafile;
    private static MameMachine machine005;
    private static MameRom rom005;
    private static MameBiosset biosset005;
    private static MameDisk disk005;
    private static MameDeviceref deviceref005;
    private static MameSample sample005;
    private static MameChip chip005;
    private static MameDisplay display005;
    private static MameSound sound005;
    private static MameInput input005;
    private static MameInputControl inputControl005;
    private static MameDipswitch dipswitch005;
    private static MameDipvalue dipvalue005;
    private static MameConfiguration configuration005;
    private static MameConfsetting confsetting005;
    private static MamePort port005;
    private static MameAnalog analog005;
    private static MameAdjuster adjuster005;
    private static MameDriver driver005;
    private static MameDevice device005;
    private static MameDeviceInstance deviceInstance005;
    private static MameDeviceExtension deviceExtension005;

    @Autowired
    private MameService mameService;


    @BeforeClass
    public static void initialize() throws Exception {
        MameParser parser = new MameParser();

        InputStream is = MameServiceTest.class.getClassLoader().getResourceAsStream("mame-info.xml");
        datafile = parser.parse(is);
        for (MameMachine m : datafile.getMachines()) {
            if (m.getName().equals("005")) {
                machine005 = m;
                break;
            }
        }
        for (MameRom rom : machine005.getRoms()) {
            if (rom.getName().equals("1346b.cpu-u25")) {
                rom005 = rom;
                break;
            }
        }
        biosset005 = machine005.getBiossets().iterator().next();
        disk005 = machine005.getDisks().iterator().next();
        deviceref005 = machine005.getDevicerefs().get(0);
        sample005 = machine005.getSamples().get(0);
        chip005 = machine005.getChips().iterator().next();
        display005 = machine005.getDisplays().iterator().next();
        sound005 = machine005.getSound();
        input005 = machine005.getInput();
        inputControl005 = input005.getControls().iterator().next();
        dipswitch005 = machine005.getDipswitches().get(0);
        dipvalue005 = dipswitch005.getDipvalues().get(0);
        configuration005 = machine005.getConfigurations().iterator().next();
        confsetting005 = configuration005.getConfsettings().iterator().next();
        port005 = machine005.getPorts().get(0);
        analog005 = port005.getAnalogs().get(0);
        adjuster005 = machine005.getAdjusters().iterator().next();
        driver005 = machine005.getDriver();
        device005 = machine005.getDevices().iterator().next();
        deviceInstance005 = device005.getInstances().iterator().next();
        deviceExtension005 = device005.getExtensions().iterator().next();
    }

    @Test
    public void create() {
        Mame mame = mameService.create(datafile);

        Assert.assertEquals("build", "0.186 (mame0186)", mame.getBuild());
        Assert.assertEquals("debug", datafile.getDebug(), mame.getDebug());
        Assert.assertEquals("mameconfig", "10", mame.getMameconfig());
    }

    @Test
    public void createMachine() {
        Mame mame = mameService.create(datafile);
        Assert.assertEquals("machines", datafile.getMachines().size(), mame.getMachines().size());

        MameMachine m = mame.getMachines().iterator().next();

        Assert.assertEquals("name", "005", m.getName());
        Assert.assertEquals("sourcefile", "segag80r.cpp", m.getSourcefile());
        Assert.assertEquals("sampleof", "005", m.getSampleof());
        Assert.assertEquals("description", "005", m.getDescription());
        Assert.assertEquals("isbios", machine005.getIsbios(), m.getIsbios());
        Assert.assertEquals("isdevice", machine005.getIsdevice(), m.getIsdevice());
        Assert.assertEquals("ismechanical", machine005.getIsmechanical(), m.getIsmechanical());
        Assert.assertEquals("runnable", machine005.getRunnable(), m.getRunnable());
        Assert.assertEquals("cloneof", machine005.getCloneof(), m.getCloneof());
        Assert.assertEquals("romof", machine005.getRomof(), m.getRomof());
        Assert.assertEquals("sampleof", machine005.getSampleof(), m.getSampleof());
        Assert.assertEquals("year", "1981", m.getYear());
        Assert.assertEquals("manufacturer", "Sega", m.getManufacturer());
    }

    @Test
    public void createBiosset() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameBiosset biosset = machine.getBiossets().iterator().next();

        Assert.assertEquals("name", "biosset 01", biosset.getName());
        Assert.assertEquals("description", "biosset 001", biosset.getDescription());
        Assert.assertEquals("default", biosset005.getDefault(), biosset.getDefault());
    }

    @Test
    public void createRom() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameRom rom = machine.getRoms().get(0);

        Assert.assertEquals("name", "1346b.cpu-u25", rom.getName());
        Assert.assertEquals("bios", rom005.getBios(), rom.getBios());
        Assert.assertEquals("size", Long.valueOf(2048), rom.getSize());
        Assert.assertEquals("crc", "8e68533e", rom.getCrc());
        Assert.assertEquals("sha1;", "a257c556d31691068ed5c991f1fb2b51da4826db", rom.getSha1());
        Assert.assertEquals("merge", rom005.getMerge(), rom.getMerge());
        Assert.assertEquals("region", "maincpu", rom.getRegion());
        Assert.assertEquals("offset", "0", rom.getOffset());
        Assert.assertEquals("status", rom005.getStatus(), rom.getStatus());
        Assert.assertEquals("optional", rom005.getOptional(), rom.getOptional());
    }

    @Test
    public void createDisk() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDisk disk = machine.getDisks().iterator().next();

        Assert.assertEquals("name", "disk 01", disk.getName());
        Assert.assertEquals("sha1", disk005.getSha1(), disk.getSha1());
        Assert.assertEquals("merge", disk005.getMerge(), disk.getMerge());
        Assert.assertEquals("region", "maincpu", disk.getRegion());
        Assert.assertEquals("index", disk005.getIndex(), disk.getIndex());
        Assert.assertEquals("writable", "no", disk.getWritable());
        Assert.assertEquals("status", "nodump", disk.getStatus());
        Assert.assertEquals("optional", "yes", disk.getOptional());
    }

    @Test
    public void createDeviceRef() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDeviceref deviceref = machine.getDevicerefs().get(0);

        Assert.assertEquals("name", "z80", deviceref.getName());
    }

    @Test
    public void createSample() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameSample sample = machine.getSamples().get(0);

        Assert.assertEquals("name", "lexplode", sample.getName());
    }

    @Test
    public void createChip() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameChip chip = machine.getChips().iterator().next();

        Assert.assertEquals("name", "Z80", chip.getName());
        Assert.assertEquals("tag", "maincpu", chip.getTag());
        Assert.assertEquals("type", "cpu", chip.getType());
        Assert.assertEquals("clock", "3867000", chip.getClock());
    }

    @Test
    public void createDisplay() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDisplay display = machine.getDisplays().iterator().next();

        Assert.assertEquals("tag", "screen", display.getTag());
        Assert.assertEquals("type", "raster", display.getType());
        Assert.assertEquals("rotate", "270", display.getRotate());
        Assert.assertEquals("flipx", display005.getFlipx(), display.getFlipx());
        Assert.assertEquals("width", Integer.valueOf(256), display.getWidth());
        Assert.assertEquals("height", Integer.valueOf(224), display.getHeight());
        Assert.assertEquals("refresh", "59.998138", display.getRefresh());
        Assert.assertEquals("pixclock", Integer.valueOf(5156000), display.getPixclock());
        Assert.assertEquals("htotal", Integer.valueOf(328), display.getHtotal());
        Assert.assertEquals("hbend", Integer.valueOf(0), display.getHbend());
        Assert.assertEquals("hbstart", Integer.valueOf(256), display.getHbstart());
        Assert.assertEquals("vtotal", Integer.valueOf(262), display.getVtotal());
        Assert.assertEquals("vbend", Integer.valueOf(0), display.getVbend());
        Assert.assertEquals("vbstart", Integer.valueOf(224), display.getVbstart());
    }

    @Test
    public void createSound() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameSound sound = machine.getSound();

        Assert.assertEquals("channels", Integer.valueOf(1), sound.getChannels());
    }

    @Test
    public void createInput() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameInput input = machine.getInput();

        Assert.assertEquals("service", "yes", input.getService());
        Assert.assertEquals("tilt", input005.getTilt(), input.getTilt());
        Assert.assertEquals("players", Integer.valueOf(2), input.getPlayers());
        Assert.assertEquals("coins", Integer.valueOf(2), input.getCoins());
    }

    @Test
    public void createInputControl() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameInput input = machine.getInput();
        MameInputControl control = input.getControls().iterator().next();

        Assert.assertEquals("type", "joy", control.getType());
        Assert.assertEquals("player", Integer.valueOf(1), control.getPlayer());
        Assert.assertEquals("buttons", Integer.valueOf(1), control.getButtons());
        Assert.assertEquals("reqbuttons", inputControl005.getReqbuttons(), control.getReqbuttons());
        Assert.assertEquals("minimum", inputControl005.getMinimum(), control.getMinimum());
        Assert.assertEquals("maximum", inputControl005.getMaximum(), control.getMaximum());
        Assert.assertEquals("sensitivity", inputControl005.getSensitivity(), control.getSensitivity());
        Assert.assertEquals("keydelta", inputControl005.getKeydelta(), control.getKeydelta());
        Assert.assertEquals("reverse", inputControl005.getReverse(), control.getReverse());
        Assert.assertEquals("ways", "4", control.getWays());
        Assert.assertEquals("ways2", inputControl005.getWays2(), control.getWays2());
        Assert.assertEquals("ways3", inputControl005.getWays3(), control.getWays3());
    }

    @Test
    public void createDipswitch() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDipswitch dipswitch = machine.getDipswitches().get(0);

        Assert.assertEquals("name", "Coin A", dipswitch.getName());
        Assert.assertEquals("tag", "D1D0", dipswitch.getTag());
        Assert.assertEquals("mask", Long.valueOf(15), dipswitch.getMask());
    }

    @Test
    public void createDipvalue() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDipswitch dipswitch = machine.getDipswitches().get(0);
        MameDipvalue dipvalue = dipswitch.getDipvalues().get(0);

        Assert.assertEquals("name", "4 Coins/1 Credit", dipvalue.getName());
        Assert.assertEquals("value", Long.valueOf(0), dipvalue.getValue());
        Assert.assertEquals("default", dipvalue005.getDefault(), dipvalue.getDefault());
    }

    @Test
    public void createConfiguration() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameConfiguration configuration = machine.getConfigurations().iterator().next();

        Assert.assertEquals("name", "Treat Joystick as...", configuration.getName());
        Assert.assertEquals("tag", "CONFIG", configuration.getTag());
        Assert.assertEquals("mask", Integer.valueOf(1), configuration.getMask());
    }

    @Test
    public void createConfsetting() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameConfiguration configuration = machine.getConfigurations().iterator().next();
        MameConfsetting confsetting = configuration.getConfsettings().iterator().next();

        Assert.assertEquals("name", "Buttons", confsetting.getName());
        Assert.assertEquals("value", Integer.valueOf(0), confsetting.getValue());
        Assert.assertEquals("default", "yes", confsetting.getDefault());
    }

    @Test
    public void createPort() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MamePort port = machine.getPorts().get(0);

        Assert.assertEquals("tag", ":D1D0", port.getTag());
    }

    @Test
    public void createAnalog() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MamePort port = machine.getPorts().get(0);
        MameAnalog analog = port.getAnalogs().get(0);

        Assert.assertEquals("mask", Integer.valueOf(1), analog.getMask());
    }

    @Test
    public void createAdjuster() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameAdjuster adjuster = machine.getAdjusters().iterator().next();

        Assert.assertEquals("name", "VR2 - DAC Volume", adjuster.getName());
        Assert.assertEquals("default", Integer.valueOf(90), adjuster.getDefault());
    }

    @Test
    public void createDriver() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDriver driver = machine.getDriver();

        Assert.assertEquals("status", "imperfect", driver.getStatus());
        Assert.assertEquals("emulation", "good", driver.getEmulation());
        Assert.assertEquals("color", "good", driver.getColor());
        Assert.assertEquals("sound", "imperfect", driver.getSound());
        Assert.assertEquals("graphic", "good", driver.getGraphic());
        Assert.assertEquals("cocktail", driver005.getCocktail(), driver.getCocktail());
        Assert.assertEquals("protection", driver005.getProtection(), driver.getProtection());
        Assert.assertEquals("savestate", "unsupported", driver.getSavestate());
    }

    @Test
    public void createDevice() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();

        Assert.assertEquals("type", "cassette", device.getType());
        Assert.assertEquals("tag", "cassette", device.getTag());
        Assert.assertEquals("fixed_image", device005.getFixed_image(), device.getFixed_image());
        Assert.assertEquals("mandatory", device005.getMandatory(), device.getMandatory());
        Assert.assertEquals("interface", device005.getInterface(), device.getInterface());
    }

    @Test
    public void createDeviceInstance() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();
        MameDeviceInstance instance = device.getInstances().iterator().next();

        Assert.assertEquals("name", "cassette", instance.getName());
        Assert.assertEquals("briefname", "cass", instance.getBriefname());
    }

    @Test
    public void createDeviceExtension() {
        Mame mame = mameService.create(datafile);
        MameMachine machine = mame.getMachines().iterator().next();
        MameDevice device = machine.getDevices().iterator().next();
        MameDeviceExtension extension = device.getExtensions().iterator().next();

        Assert.assertEquals("name", "wav", extension.getName());
    }
}
