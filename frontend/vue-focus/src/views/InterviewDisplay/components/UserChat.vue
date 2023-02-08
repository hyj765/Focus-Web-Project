<template>
  <v-container>
    <div class="chat">
      <div class="chat-history">
        <ul>
          <li
            class="text-color"
            style="
              margin-bottom: 10px;
              text-align: center;
              margin-right: 20px;
              background-color: whitesmoke;
            "
          >
            ※ 면접관의 지시에 따라 마이크를 켜주세요.
          </li>
          <text v-for="chat in chatLog" :key="chat.id" class="clearfix pt-4">
            <div class="message-data align-right" style="padding-right: 17px">
              <span class="message-data-name">{{ chat[1].clientData }}</span>
            </div>
            <div class="message other-message float-right">
              {{ chat[0] }}
            </div>
          </text>
        </ul>
      </div>

      <div id="chat" class="chat-message clearfix">
        <form id="chatForm" action="#" @submit.prevent="chatOccur(msg)">
          <textarea
            id="message-to-send"
            v-model="msg"
            name="message-to-send"
            placeholder="메세지를 입력하세요."
            rows="3"
            style="
              background-color: whitesmoke;
              padding-left: 10px;
              padding-right: 5px;
            "
          ></textarea>
          <button type="submit" style="padding-left: 5px; padding-right: 5px">
            전송
          </button>
        </form>
      </div>
    </div>
  </v-container>
</template>

<script>
export default {
  name: 'UserChat',
  props: {
    // eslint-disable-next-line
    chatLog: Array,
  },

  data: function () {
    return {
      msg: '',
    };
  },
  methods: {
    chatOccur(msg) {
      // eslint-disable-next-line
      this.$emit('sendMessage', msg);
      this.msg = '';
    },
  },
};
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Lato:400,700);
*,
*:before,
*:after {
  box-sizing: border-box;
}
.chat {
  width: 510px;
  height: 670px;
  float: left;
  background: white;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  color: #434651;
}
.chat {
  padding: 20px;
  /* border-bottom: 2px solid white; */
}
.chat {
  float: left;
}
.chat {
  float: left;
  margin-top: 0px;
}
.chat {
  font-weight: bold;
  font-size: 16px;
}
.chat {
  color: black;
}

.text-color {
  color: red;
  font-size: 18px;
}

.chat .fa-star {
  float: right;
  color: #d8dadf;
  font-size: 20px;
  margin-top: 12px;
}
.chat .chat-history {
  /* padding: 30px 30px 0px; */
  border-bottom: 2px solid white;
  overflow-y: scroll;
  height: 490px;
}
.chat .chat-history .message-data {
  margin-bottom: 15px;
}
.chat .chat-history .message-data-time {
  color: #a8aab1;
  padding-left: 6px;
}
.chat .chat-history .message {
  color: black;
  margin-right: 17px;
  padding-left: 12px;
  padding-right: 12px;
  line-height: 26px;
  font-size: 16px;
  border-radius: 7px;
  margin-bottom: 20px;
  /* width: 90%; */
  /* position: relative; */
}
.chat .chat-history .message:after {
  bottom: 100%;
  left: 7%;
  border: solid transparent;
  content: ' ';
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
  border-bottom-color: #86bb71;
  border-width: 10px;
  margin-left: -10px;
}
.chat .chat-history .my-message {
  background: #86bb71;
}
.chat .chat-history .other-message {
  background: whitesmoke;
}
.chat .chat-history .other-message:after {
  border-bottom-color: white;
  left: 93%;
}
.chat .chat-message {
  padding: 30px;
}
.chat .chat-message textarea {
  width: 100%;
  border: none;
  padding: 10px 10px;
  /* font: 14px/22px 'Lato', Arial, sans-serif; */
  /* margin-bottom: 10px; */
  border-radius: 5px;
  resize: none;
}
.chat .chat-message .fa-file-o,
.chat .chat-message .fa-file-image-o {
  font-size: 16px;
  color: gray;
  cursor: pointer;
}
.chat .chat-message button {
  float: right;
  color: black;
  font-size: 16px;
  text-transform: uppercase;
  border: none;
  cursor: pointer;
  font-weight: bold;
  background: #f2f5f8;
}
.chat .chat-message button:hover {
  color: #75b1e8;
}
.clearfix:after {
  visibility: hidden;
  display: block;
  font-size: 0;
  content: ' ';
  clear: both;
  height: 0;
}

#chat {
  display: inline;
}
</style>
