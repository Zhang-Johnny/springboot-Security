package org.sang.common;

/**辅助接口
 *Created by johnny on 2018/10/14.
 * */
public interface IAuxiliaryTool {
    IModuleEntity addAutomaticModul(Class<IModuleEntity> klass);
    <T> T addAutomaticTimeOrId(Class<T> klass);
}
