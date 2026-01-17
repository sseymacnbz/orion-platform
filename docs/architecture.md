# Architecture Overview

Orion Platform follows a microservice architecture where each service owns
its own database and communicates via REST and asynchronous events.

## Communication
- Synchronous: REST (OpenFeign)
- Asynchronous: RabbitMQ

## Data Ownership
Each microservice has its own database schema.

## Cross-cutting Concerns
- Authentication via centralized Auth Service
- Caching with Redis
- Centralized logging and monitoring
