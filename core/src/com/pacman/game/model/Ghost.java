package com.pacman.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.pacman.game.screen.EndScreen;

import java.util.ArrayList;

public abstract class Ghost extends MoveableElement {

    private float vitesse = 0.1f;

    protected boolean doitSortir=false;

    public Ghost(Vector2 pos, World w) {
        super(pos, w);
        _vel = new Vector2(0, 0);
    }

    public void setDoitSortir(boolean doitSortir) {
        this.doitSortir = doitSortir;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(float vitesse) {
        this.vitesse = vitesse;
    }

    @Override
    public World getWorld() {
        return _world;
    }

    @Override
    public Vector2 getPosition() {
        return _pos;
    }

    @Override
    public void setPosition(Vector2 v) {
        _pos = v;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(_pos.x, _pos.y, getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return "Ghost [position=" + _pos + ", world=" + _world + "]";
    }

    @Override
    public Vector2 getVelocity() {
        return _vel;
    }

    @Override
    public void setVelocity(Vector2 v) {
        _vel = v;
    }

    @Override
    public boolean getEscape() { return _escape; }

    @Override
    public void setEscape(Boolean escape) { _escape = escape;}

    @Override
    public boolean getDead() { return _dead; }

    @Override
    public void setDead(Boolean dead) { _dead = dead;}


    public Object direction() {
        if (_vel.x > 0) {
            return direction.RIGHT;
        } else if (_vel.x < 0) {
            return direction.LEFT;
        } else if (_vel.y > 0) {
            return direction.UP;
        } else if (_vel.y < 0) {
            return direction.DOWN;
        }
        return direction.NONE;
    }

    public abstract void deplacement();

    protected void deplacementAlea() {
        GameElement geUp, geDown, geRight, geLeft;
        ArrayList<Vector2> velocityPossible = new ArrayList<Vector2>();
        if (((int) _pos.y + 1) < _world.getMaze().getHeight() - 1) {
            geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
            if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                velocityPossible.add(new Vector2(0, vitesse));
            }
        }
        if ((int) _pos.y - 1 >= 0) {
            geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
            if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                velocityPossible.add(new Vector2(0, -vitesse));
            }
        }
        if (((int) _pos.x + 1) < _world.getMaze().getWidth() - 1) {
            geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
            if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                velocityPossible.add(new Vector2(vitesse, 0));
            }
        }
        if (((int) _pos.x - 1) >= 0) {
            geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);
            if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                velocityPossible.add(new Vector2(-vitesse, 0));
            }
        }

        int direction = (int) (Math.random() * (velocityPossible.size()));
        _vel = velocityPossible.get(direction);
        _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
        _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;

    }


    protected void sortirMaison() {
        GameElement geUp, geDown, geRight, geLeft;
        geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
        geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
        geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
        geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);

        Barriere sortie=this._world.getMaze().getBarierres()[0];
        if(sortie._pos.x<=_pos.x && !(geLeft instanceof Block)){
            _vel = (new Vector2(-vitesse, 0));
        }
        else if(!(geDown instanceof Block) && sortie._pos.y<_pos.y){
            _vel = (new Vector2(0, -vitesse));
        }
        else if( !(geUp instanceof Block) && sortie._pos.y>_pos.y){
            _vel = (new Vector2(0, vitesse));
        }


        //System.out.println(_vel);
        _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
        _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;

    }

    protected void deplacementFuite() {
        float diff_x = _world.getPacman().getPosition().x - _pos.x;
        float diff_y = _world.getPacman().getPosition().y - _pos.y;
        GameElement geUp, geDown, geRight, geLeft;
        geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
        geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
        geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
        geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);

        if (diff_x == 0 && diff_y == 0) {
            _vel = (new Vector2(0, 0));
        } else if (diff_x == 0) {
            if (diff_y > 0) {
                if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));

                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                }
            } else {
                if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                }
            }

        } else if (diff_y == 0) {
            if (diff_x > 0) {
                if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                }
            } else {
                if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                }
            }
        } else if (Math.abs(diff_x) > Math.abs((diff_y))) {
            if (diff_x > 0) {
                if (diff_y > 0) {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            } else {
                if (diff_y > 0) {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            }
        } else {
            if (diff_y > 0) {
                if (diff_x > 0) {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            } else {
                if (diff_x > 0) {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            }
        }


        _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
        _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
    }

    protected void deplacementMinim() {
        float diff_x = _world.getPacman().getPosition().x - _pos.x;
        float diff_y = _world.getPacman().getPosition().y - _pos.y;
        GameElement geUp, geDown, geRight, geLeft;
        geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
        geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
        geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
        geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);

        if (diff_x == 0 && diff_y == 0) {
            _vel = (new Vector2(0, 0));
        } else if (diff_x == 0) {
            if (diff_y < 0) {
                if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));

                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                }
            } else {
                if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                }
            }

        } else if (diff_y == 0) {
            if (diff_x < 0) {
                if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                }
            } else if (diff_x > 0) {
                if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                }
            }
        } else if (Math.abs(diff_x) < Math.abs((diff_y))) {
            if (diff_x < 0) {
                if (diff_y < 0) {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else if (diff_y > 0) {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            } else {
                if (diff_y < 0) {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else if (diff_y > 0) {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            }
        } else {
            if (diff_y < 0) {
                if (diff_x < 0) {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else if (diff_x > 0) {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            } else {
                if (diff_x < 0) {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else if (diff_x > 0) {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            }
        }


        _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
        _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
    }

    protected void deplacementMaison() {
        float diff_x = 13 - _pos.x;
        float diff_y = 14- _pos.y;
        GameElement geUp, geDown, geRight, geLeft;
        geUp = _world.getMaze().get((int) _pos.x, (int) _pos.y + 1);
        geDown = _world.getMaze().get((int) _pos.x, (int) _pos.y - 1);
        geRight = _world.getMaze().get((int) _pos.x + 1, (int) _pos.y);
        geLeft = _world.getMaze().get((int) _pos.x - 1, (int) _pos.y);

        if (diff_x == 0 && diff_y == 0) {
            _vel = (new Vector2(0, 0));
        } else if (diff_x == 0) {
            if (diff_y < 0) {
                if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));

                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                }
            } else {
                if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                }
            }

        } else if (diff_y == 0) {
            if (diff_x < 0) {
                if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                }
            } else if (diff_x > 0) {
                if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                    _vel = (new Vector2(vitesse, 0));
                } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                    _vel = (new Vector2(0, vitesse));
                } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                    _vel = (new Vector2(0, -vitesse));
                } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                    _vel = (new Vector2(-vitesse, 0));
                }
            }
        } else if (Math.abs(diff_x) < Math.abs((diff_y))) {
            if (diff_x < 0) {
                if (diff_y < 0) {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else if (diff_y > 0) {
                    if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            } else {
                if (diff_y < 0) {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    }
                } else if (diff_y > 0) {
                    if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    }
                }
            }
        } else {
            if (diff_y < 0) {
                if (diff_x < 0) {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else if (diff_x > 0) {
                    if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            } else {
                if (diff_x < 0) {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    }
                } else if (diff_x > 0) {
                    if (!(geUp instanceof Block || geUp instanceof Barriere)) {
                        _vel = (new Vector2(0, vitesse));
                    } else if (!(geRight instanceof Block || geRight instanceof Barriere)) {
                        _vel = (new Vector2(vitesse, 0));
                    } else if (!(geDown instanceof Block || geDown instanceof Barriere)) {
                        _vel = (new Vector2(0, -vitesse));
                    } else if (!(geLeft instanceof Block || geLeft instanceof Barriere)) {
                        _vel = (new Vector2(-vitesse, 0));
                    }
                }
            }
        }


        _pos.x = (float) Math.round((_pos.x + _vel.x) * 10) / 10;
        _pos.y = (float) Math.round((_pos.y + _vel.y) * 10) / 10;
    }
}
