package com.pacman.game.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.game.view.TextureFactory;

public class Pacman extends MoveableElement
{
	Vector2 nextVelocity;
	
    public Pacman(Vector2 position, World world)
    {
        super(position, world);
        _vel=new Vector2(0,0);
        nextVelocity=new Vector2(0,0);
    }

    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public Vector2 getVelocity() { return _vel; }

    @Override
    public void setPosition(Vector2 position) {
        this._pos = position;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this._vel = velocity;
    }
    
    public void setNextVelocity(Vector2 velocity) {
        this.nextVelocity = velocity;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    public World getWorld() {
        return _world;
    }

    public void setWorld(World world) {
        this._world = world;
    }

    @Override
    public String toString() {
        return "Pacman [position=" + _pos + ", world=" + _world + "]";
    }

    public void deplacement(){
        Rectangle rectGe=new Rectangle();
        if(_pos.x%1==0&&_pos.y%1==0) {
        	GameElement ge = null;
        	if(nextVelocity.x>0) {
        		ge=_world.getMaze().get((int)_pos.x+1,(int) _pos.y);
        	}
        	if(nextVelocity.x<0) {
        		ge=_world.getMaze().get((int)_pos.x-1,(int) _pos.y);
        	}
        	if(nextVelocity.y>0) {
        		ge=_world.getMaze().get((int)_pos.x,(int) _pos.y+1);
        	}
        	if(nextVelocity.y<0) {
        		ge=_world.getMaze().get((int)_pos.x,(int) _pos.y-1);
        	}
        	if(!(ge instanceof Block||ge instanceof Barriere)) {
            	_pos.x=(float)Math.round((_pos.x + nextVelocity.x)*10)/10;
                _pos.y=(float)Math.round((_pos.y + nextVelocity.y)*10)/10;
                _vel=nextVelocity;
        	}
        	else {
        		if(_vel.x>0) {
            		ge=_world.getMaze().get((int)_pos.x+1,(int) _pos.y);
            	}
            	if(_vel.x<0) {
            		ge=_world.getMaze().get((int)_pos.x-1,(int) _pos.y);
            	}
            	if(_vel.y>0) {
            		ge=_world.getMaze().get((int)_pos.x,(int) _pos.y+1);
            	}
            	if(_vel.y<0) {
            		ge=_world.getMaze().get((int)_pos.x,(int) _pos.y-1);
            	}
            	if(!(ge instanceof Block||ge instanceof Barriere)) {
                	_pos.x=(float)Math.round((_pos.x + _vel.x)*10)/10;
                    _pos.y=(float)Math.round((_pos.y + _vel.y)*10)/10;
            	}
        	}
        }
        else {
        	_pos.x=(float)Math.round((_pos.x + _vel.x)*10)/10;
            _pos.y=(float)Math.round((_pos.y + _vel.y)*10)/10;
        }
       /* if (nextVelocity.x>0) {
        	ge=_world.getMaze().get(getPositionNextVelocity().x, getPositionNextVelocity().y);
        }*/
    }
    
	@Override
	public Rectangle getRectangle() {
		return new Rectangle(_pos.x,_pos.y,getWidth(),getHeight());
	}
	
	public Vector2 getPositionNextVelocity() {
		float x=(float)Math.round((_pos.x + nextVelocity.x)*10)/10;
		float y=(float)Math.round((_pos.y + nextVelocity.y)*10)/10;
		return new Vector2(x,y);
	}
	
	public Rectangle getRectangleNextVelocity() {
    	float x=(float)Math.round((_pos.x + nextVelocity.x)*10)/10;
        float y=(float)Math.round((_pos.y + nextVelocity.y)*10)/10;
		return new Rectangle(x,y,getWidth(),getHeight());
	}

	public Object direction() {
		if(_vel.x>0) {
			return direction.RIGHT;
		}
		else if(_vel.x<0) {
			return direction.LEFT;
		}
		else if(_vel.y>0) {
			return direction.UP;
		}
		else if(_vel.y<0) {
			return direction.DOWN;
		}
		return direction.NONE;
	}
}