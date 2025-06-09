<template>
  <div>
    <video style="width: 1200px;height:500px;object-fit: fill" ref="video" controls autoplay></video>
  </div>
</template>

<script>
import flvjs from 'flv.js';

export default {
  name: 'FlvPlayer',
  props: {
    url: {
      type: String,
      default:'https://106.14.214.143:28868/video/live/psw.flv'
    }
  },
  mounted() {
    if (flvjs.isSupported()) {
      const videoElement = this.$refs.video;
      const flvPlayer = flvjs.createPlayer({
        type: 'flv',
        url: this.url
      });
      flvPlayer.attachMediaElement(videoElement);
      flvPlayer.load();
      flvPlayer.play();
    }
  },
  beforeDestroy() {
    if (this.flvPlayer) {
      this.flvPlayer.destroy();
    }
  }
};
</script>
