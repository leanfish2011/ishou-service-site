#!/bin/bash

# 根据各个项目修改：项目名称、sdk名称、服务名称
project_name="ishou-service-site"
sdk_name="site-sdk"
service_name="site-service"

# 引入脚本
source ../../shell/docker_build_service.sh

# 调用引入脚本中方法
build_image $project_name $sdk_name $service_name
