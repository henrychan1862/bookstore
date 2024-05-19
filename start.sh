#!/bin/bash

sudo docker build . -t bookstore:v1
sudo docker-compose up