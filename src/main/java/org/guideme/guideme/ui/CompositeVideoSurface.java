package org.guideme.guideme.ui;

import org.eclipse.swt.widgets.Composite;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurface;
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapter;

import java.lang.reflect.Field;

/**
 * Implementation of a video surface component that uses an SWT Composite.
 * <p>
 * <em>This class might get added to a future version of vlcj.</em>
 */
public class CompositeVideoSurface extends VideoSurface {

    /**
	 * 
	 */
	private static final long serialVersionUID = -602915292932819950L;
	private final long compositeHandle;

    public CompositeVideoSurface(Composite composite, VideoSurfaceAdapter videoSurfaceAdapter) {
        super(videoSurfaceAdapter);
        this.compositeHandle = getCompositeHandle(composite);
    }

    @Override
    public void attach(MediaPlayer mediaPlayer) {
        videoSurfaceAdapter.attach(mediaPlayer, compositeHandle);
    }

    /**
     * Written to handle SWT differences between Linux, MacOS, and Windows
     * Uses reflection, but only runs once. No cross-platform alternatives seem to exist
     *
     * @return long Composite ID
     */
    private long getCompositeHandle(Composite composite) {
        try {
            Class<?> objectClass = composite.getClass();
            for (Field field : objectClass.getFields()) {
                if (field.getName().equals("handle")) {
                    Object handle = field.get(composite);
                    if (handle instanceof Integer) {
                        return ((Integer) handle).longValue();
                    }
                    else {
                        return (long) handle;
                    }
                }
                if (field.getName().equals("view")) {
                    Class<?> viewObjectClass = field.get(composite).getClass();
                    for (Field viewField : viewObjectClass.getFields()) {
                        if (viewField.getName().equals("id")) {
                            return (long)viewField.get(field.get(composite));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Access exception retrieving handle to SWT object");
        }

        throw new RuntimeException("Could not determine handle to SWT object");
    }
}
