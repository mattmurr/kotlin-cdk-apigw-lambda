SHELL = /bin/bash -c

build :
		./gradlew buildZip

synth : build
		cd cdk; cdk synth

deploy : synth
		cd cdk; cdk deploy

cdk-bootstrap :
		cd cdk; cdk bootstrap

local-lambda : build
		sam local start-lambda -t cdk/cdk.out/CdkStack.template.json

local-apigw : build
		sam local start-api -t cdk/cdk.out/CdkStack.template.json

.PHONY: build synth deploy
.DEFAULT_GOAL := synth