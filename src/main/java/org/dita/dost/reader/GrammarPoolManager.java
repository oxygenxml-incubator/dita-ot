/*
 * This file is part of the DITA Open Toolkit project.
 *
 * Copyright 2009 Really Strategies, Inc.
 *
 * See the accompanying LICENSE file for applicable license.
 */
package org.dita.dost.reader;

import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.dita.dost.util.XMLGrammarPoolImplUtils;
import org.ditang.relaxng.defaults.pool.RNGDefaultsEnabledSynchronizedXMLGrammarPoolImpl;

/**
 * Manages creation and access to a master Xerces grammar pool.
 * The grammar pool is managed as a ThreadLocal variable so it can
 * be used across Ant task invocations.
 */
public final class GrammarPoolManager {

    private static final ThreadLocal<XMLGrammarPool> grammarPool = new ThreadLocal<>();

    /**
     * Get grammar pool
     *
     * @return grammar pool instance
     */
    public static XMLGrammarPool getGrammarPool() {
        XMLGrammarPool pool = grammarPool.get();
        if (pool == null) {
            try {
                pool = new RNGDefaultsEnabledSynchronizedXMLGrammarPoolImpl();
                grammarPool.set(pool);
            } catch (final Exception e) {
                System.out.println("Failed to create Xerces grammar pool for caching DTDs and schemas");
            }
        }
        return pool;
    }

}

