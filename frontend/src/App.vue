<template>
  <n-config-provider :theme="null">
    <div class="min-h-screen flex flex-col">
      <header class="border-b bg-white">
        <div class="max-w-6xl mx-auto p-3 flex items-center justify-between">
          <h1 class="font-bold">Peach Garden Forum</h1>
          <div>
            <RouterLink class="mx-2" to="/">首页</RouterLink>
            <RouterLink class="mx-2" to="/chat">聊天</RouterLink>
            <RouterLink class="mx-2" to="/login">登录</RouterLink>
            <RouterLink class="mx-2" to="/register">注册</RouterLink>
          </div>
        </div>
      </header>
      <main class="flex-1">
        <RouterView />
      </main>
    </div>
  </n-config-provider>
  
</template>

<script setup lang="ts">
import { NConfigProvider } from 'naive-ui'
</script>

<style scoped>
</style>

