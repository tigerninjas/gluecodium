/*
 * Copyright (C) 2017 HERE Global B.V. and its affiliate(s). All rights reserved.
 *
 * This software, including documentation, is protected by copyright controlled by
 * HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the prior written
 * consent of HERE Global B.V. This material also contains confidential information,
 * which may not be disclosed to others without prior written consent of HERE Global B.V.
 *
 */

package com.here.ivi.api.generator.common.modelbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Context of each building step. Used to propagate data between parent- and child-steps. Additional
 * fields should be added if more data is needed in the future.
 */
public final class ModelBuilderContext<E> {

  public final List<E> previousResults = new ArrayList<>();
  public final List<E> currentResults = new ArrayList<>();
}