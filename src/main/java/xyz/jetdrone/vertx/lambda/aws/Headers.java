/*
 * Copyright 2019 Paulo Lopes.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */
package xyz.jetdrone.vertx.lambda.aws;

/**
 * Well known AWS Lambda headers
 */
public interface Headers {

  String LAMBDA_RUNTIME_AWS_REQUEST_ID = "Lambda-Runtime-Aws-Request-Id";
  String LAMBDA_RUNTIME_DEADLINE_MS = "Lambda-Runtime-Deadline-Ms";
  String LAMBDA_RUNTIME_INVOKE_FUNCTION_ARN = "Lambda-Runtime-Invoke-Function-Arn";
  String LAMBDA_RUNTIME_TRACE_ID = "Lambda-Runtime-Trace-Id";
  String LAMBDA_RUNTIME_CLIENT_CONTEXT = "Lambda-Runtime-Client-Context";
  String LAMBDA_RUNTIME_COGNITO_IDENTITY = "Lambda-Runtime-Cognito-Identity";
}
