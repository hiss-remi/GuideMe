package org.guideme.guideme.ui;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

/**
 * Implementation of a media player for SWT.
 * <p>
 * <em>This class might get added to a future version of vlcj.</em>
 * <p>
 * FIXME Ideally there should also be an SwtEmbeddedMediaPlayerComponent that encapsulates the video surface.
 */
public class SwtEmbeddedMediaPlayer extends MediaPlayer {

    private CompositeVideoSurface videoSurface;

    public SwtEmbeddedMediaPlayer(libvlc_instance_t instance) {
        super(instance);
    }

    public void setVideoSurface(CompositeVideoSurface videoSurface) {
        this.videoSurface = videoSurface;
    }

    public void attachVideoSurface() {
        videoSurface.attach(this);
    }

    @Override
    protected final void onBeforePlay() {
        attachVideoSurface();
    }
}