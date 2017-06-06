package BachelorArbeitWs1617;

import animal.exchange.AnimationImporter;
import animal.main.Animation;

import java.io.InputStream;

/**
 * Created by deniz on 30.05.17.
 */
public class MYAnimationImporter extends AnimationImporter {
    @Override
    public Animation importAnimationFrom(InputStream in, String filename) {
        return null;
    }

    @Override
    public String getDefaultExtension() {
        return null;
    }

    @Override
    public String getFormatDescription() {
        return null;
    }

    @Override
    public String getMIMEType() {
        return null;
    }
}
