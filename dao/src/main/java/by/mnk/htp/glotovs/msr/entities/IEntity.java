package by.mnk.htp.glotovs.msr.entities;

import java.io.Serializable;

/**
 * Created by Sefire on 24.10.2016.
 */
public interface IEntity<K extends Serializable> extends Serializable, Cloneable{
    K getId();
    void setId(K id);
}
