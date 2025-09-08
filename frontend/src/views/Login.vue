<template>
  <div class="max-w-md mx-auto p-6">
    <h2 class="text-xl font-semibold mb-4">登录</h2>
    <n-form :model="form" @submit.prevent="onSubmit">
      <n-form-item label="邮箱">
        <n-input v-model:value="form.email" placeholder="you@example.com" />
      </n-form-item>
      <n-form-item label="密码">
        <n-input type="password" v-model:value="form.password" />
      </n-form-item>
      <n-button type="primary" @click="onSubmit">登录</n-button>
    </n-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { NForm, NFormItem, NInput, NButton, useMessage } from 'naive-ui'
import axios from 'axios'

const API = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'
const message = useMessage()

const form = reactive({ email: '', password: '' })

async function onSubmit() {
  try {
    const { data } = await axios.post(`${API}/auth/login`, form)
    message.success('登录成功')
    console.log('login response', data)
  } catch (err: any) {
    message.error(err?.response?.data?.error || '登录失败')
  }
}
</script>

