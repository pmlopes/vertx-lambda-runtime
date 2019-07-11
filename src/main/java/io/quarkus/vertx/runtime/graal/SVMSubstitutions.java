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

// WARNING: This is a nasty hack to allow this project to be used by Quarkus which assumes that no-one else includes
// WARNING: substitutions in their jars or builds native images
package io.quarkus.vertx.runtime.graal;

/// TODO: remove me when netty 4.1.36 is the default on vertx-core

import com.oracle.svm.core.annotate.*;
import io.vertx.core.Vertx;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.resolver.DefaultResolverProvider;
import io.vertx.core.spi.resolver.ResolverProvider;

/**
 * This substitution forces the usage of the blocking DNS resolver
 */
@TargetClass(className = "io.vertx.core.spi.resolver.ResolverProvider")
final class TargetResolverProvider {

  @Substitute
  public static ResolverProvider factory(Vertx vertx, AddressResolverOptions options) {
    return new DefaultResolverProvider();
  }
}
