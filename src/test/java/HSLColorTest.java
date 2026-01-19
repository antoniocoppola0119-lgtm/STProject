import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {

    @Test
    public void testRGBtoHSLandBack() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(100, 150, 200);

        int h = color.getHue();
        int s = color.getSaturation();
        int l = color.getLuminence();

        HSLColor converted = new HSLColor();
        converted.initRGBbyHSL(h, s, l);

        assertEquals(color.getRed(), converted.getRed(), 1);
        assertEquals(color.getGreen(), converted.getGreen(), 1);
        assertEquals(color.getBlue(), converted.getBlue(), 1);
    }

    /*@Test
    public void testGreyscaleConversion() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(120, 120, 120);

        assertEquals(0, color.getSaturation());
        assertEquals(color.getRed(), color.getGreen());
        assertEquals(color.getGreen(), color.getBlue());
    }

    /*@Test
    public void testSetHueWrapAround() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0);

        int oldRed = color.getRed();
        color.setHue(color.getHue() + 255);

        assertNotEquals(oldRed, color.getRed());
    }

    @Test
    public void testSetSaturationBounds() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(50, 100, 150);

        color.setSaturation(-10);
        assertEquals(0, color.getSaturation());

        color.setSaturation(300);
        assertEquals(255, color.getSaturation());
    }

    @Test
    public void testSetLuminenceBounds() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(50, 100, 150);

        color.setLuminence(-20);
        assertEquals(0, color.getLuminence());

        color.setLuminence(300);
        assertEquals(255, color.getLuminence());
    }*/

    @Test
    public void testReverseColorChangesHue() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(255, 0, 0);

        int oldHue = color.getHue();
        color.reverseColor();

        assertNotEquals(oldHue, color.getHue());
    }

    @Test
    public void testBrightenIncreasesLuminence() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(50, 50, 50);

        int oldLum = color.getLuminence();
        color.brighten(1.5f);

        assertTrue(color.getLuminence() >= oldLum);
    }

    @Test
    public void testBlendChangesColor() {
        HSLColor color = new HSLColor();
        color.initHSLbyRGB(0, 0, 0);

        color.blend(255, 255, 255, 0.5f);

        assertTrue(color.getRed() > 0);
        assertTrue(color.getGreen() > 0);
        assertTrue(color.getBlue() > 0);
    }
}

