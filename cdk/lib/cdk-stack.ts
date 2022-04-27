import { Stack, StackProps } from 'aws-cdk-lib';
import { Construct } from 'constructs';
import { Function, Code, Runtime } from 'aws-cdk-lib/aws-lambda'
import { LambdaRestApi } from 'aws-cdk-lib/aws-apigateway'
import * as path from "path";

export class CdkStack extends Stack {
  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    const myFunction = new Function(this, 'AddFiveFunc', {
      runtime: Runtime.JAVA_11,
      handler: 'AddFive.Handler',
      code: Code.fromAsset(path.join(__dirname, '../../app/build/distributions/app.zip'))
    })

    new LambdaRestApi(this, 'RestAPI', {
      handler: myFunction,
      proxy: true
    })
  }
}
