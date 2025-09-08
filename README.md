# Peach-Garden-Forum

一个简洁的实时聊天论坛项目，支持主题讨论与即时消息，旨在提供轻量、易扩展的交流社区基础工程。

## 功能特性
- 实时聊天：基于 WebSocket 的频道群聊/大厅聊天。
- 主题与帖子：发帖、回复、引用与@提及。
- 用户系统：注册/登录、头像、个人资料与权限控制。
- 通知提醒：新消息/回复通知（站内/浏览器）。
- 搜索与筛选：按关键字、作者、时间范围检索。
- 管理功能：帖子管理、用户禁言/封禁（可选）。

## 技术栈（占位）
> 由于当前仓库尚未加入具体代码，以下为建议占位，待落地实现后更新为实际技术栈。
- 后端：Node.js + Express / NestJS 或 Java Spring Boot 或 Python FastAPI
- 实时通信：Socket.IO / 原生 WebSocket
- 数据库：PostgreSQL / MySQL / MongoDB（其一）
- 缓存与队列：Redis（可选）
- 前端：React / Vue / Svelte（其一）
- 部署：Docker / Docker Compose（可选）

## 本地开发（通用流程）
1. 克隆仓库：`git clone git@github.com:pinklemon123/Peach-Garden-Forum.git`
2. 切换目录：`cd Peach-Garden-Forum`
3. 准备环境：
   - 选择并安装对应技术栈（示例：Node.js 18+ 与 pnpm/yarn）
   - 安装数据库并创建数据库实例
4. 配置环境变量：
   - 复制 `.env.example` 为 `.env`（暂无可先创建），并按需填入：
     - `DATABASE_URL`
     - `REDIS_URL`（可选）
     - `JWT_SECRET`
     - `PORT`
5. 安装依赖并运行（示例）：
   - `pnpm install`
   - `pnpm dev`

> 注：实际启动命令会随选定框架而不同，后续代码落地后在此更新为准确命令。

## 目录结构（规划示例）
```
Peach-Garden-Forum/
├─ backend/         # 后端服务（API、WebSocket、业务逻辑）
├─ web/             # 前端应用（Web UI）
├─ docs/            # 文档与设计草案
├─ scripts/         # 工具脚本（数据迁移、运维脚本等）
└─ README.md
```

## Roadmap（后续迭代）
- 私信/点对点聊天
- 富文本与表情支持、消息撤回/编辑
- 文件/图片上传与内容审核
- 频道权限与分级管理
- 推送通知（FCM/APNs/Web Push）
- 日志、监控与告警接入

## 贡献指南
- 提交前请运行格式化与测试（后续补充具体命令）。
- 提交信息建议遵循 Conventional Commits（如：`feat: ...`、`fix: ...`、`docs: ...`）。
- 欢迎提交 Issue/PR 讨论新特性或优化建议。

## 许可证
待定（建议 MIT）。

