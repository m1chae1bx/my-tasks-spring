#!/bin/bash
# A Shell Script To Deploy To Do App Spring Backend
# Michael Louie Boñon - 29/Jun/2021

# Serve Backend (Spring)
cd "$(dirname "$0")"
echo $PWD
./mvnw spring-boot:run