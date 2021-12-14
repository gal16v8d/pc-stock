package com.gsdd.pcstock.util;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VideoPropertiesUtil {

  public static String getFileResolutionXuggler(String fullPath) {
    IContainer container = null;
    IStreamCoder videoCoder = null;
    try {
      container = IContainer.make();
      int result = container.open(fullPath, IContainer.Type.READ, null);
      if (result < 0) {
        throw new RuntimeException("could not find video stream in container: " + fullPath);
      } else {
        int numStreams = container.getNumStreams();
        int videoStreamId = -1;

        for (int i = 0; i < numStreams; i++) {
          IStream stream = container.getStream(i);
          IStreamCoder coder = stream.getStreamCoder();
          if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
            videoStreamId = i;
            videoCoder = coder;
            break;
          }
        }

        if (videoStreamId == -1) {
          throw new RuntimeException("could not find video stream in container: " + fullPath);
        }
        if (videoCoder.open() < 0) {
          throw new RuntimeException("could not open video decoder for container: " + fullPath);
        }

        int height = videoCoder.getHeight();
        int width = videoCoder.getWidth();
        return width + "x" + height;
      }
    } finally {
      if (videoCoder != null) {
        videoCoder.close();
      }
      if (container != null) {
        container.close();
      }
    }

  }
}
