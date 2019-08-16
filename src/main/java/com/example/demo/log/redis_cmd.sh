#!/usr/bin/env bash
set key value
get key
del key

# 设定过期时间,以秒计
expire key seconds

#
expireat key timestamp