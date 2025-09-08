<template>
  <div class="max-w-6xl mx-auto p-4">
    <h2 class="text-xl font-semibold mb-4">大厅聊天</h2>
    <div class="border rounded p-3 h-80 overflow-auto bg-white mb-3">
      <div v-for="(m,i) in messages" :key="i" class="text-sm mb-1">
        {{ m }}
      </div>
    </div>
    <div class="flex gap-2">
      <input v-model="text" class="flex-1 border rounded px-3 py-2" placeholder="输入消息" @keydown.enter="send" />
      <button class="px-4 py-2 bg-blue-600 text-white rounded" @click="send">发送</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onBeforeUnmount } from 'vue'

const messages = ref<string[]>([])
const text = ref('')
let ws: WebSocket | null = null

const WS_URL = import.meta.env.VITE_WS_URL || 'ws://localhost:8080/ws/chat'

onMounted(() => {
  ws = new WebSocket(WS_URL)
  ws.onmessage = (ev) => {
    messages.value.push(ev.data)
  }
})

onBeforeUnmount(() => {
  ws?.close()
})

function send() {
  if (!ws || ws.readyState !== WebSocket.OPEN || !text.value) return
  ws.send(text.value)
  text.value = ''
}
</script>

