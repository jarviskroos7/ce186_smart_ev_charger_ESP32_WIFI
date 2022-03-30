/*
  LinuxConsole.h - Linux STDIN/STDOUT library for Wiring
  Copyright (c) 2015 Hristo Gochkov.  All right reserved.

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

#ifndef LINUX_CONSOLE_H
#define LINUX_CONSOLE_H

#include <stdint.h>
#include <unistd.h>

#include "Stream.h"
class BridgeClass;

class LinuxConsole : public Stream{
  protected:
    
  public:
    LinuxConsole(BridgeClass &_b __attribute__((unused))){}
    void buffer(uint8_t size __attribute__((unused))){}
    void noBuffer(){}
    bool connected(){return true;}
    
    inline LinuxConsole(){}
    void begin();
    inline void begin(uint32_t baud __attribute__((unused))){}
    void end();
    virtual int available(void);
    virtual int peek(void);
    virtual int read(void);
    virtual void flush(void);
    virtual size_t write(uint8_t);
    inline size_t write(unsigned long n) { return write((uint8_t)n); }
    inline size_t write(long n) { return write((uint8_t)n); }
    inline size_t write(unsigned int n) { return write((uint8_t)n); }
    inline size_t write(int n) { return write((uint8_t)n); }
    using Print::write; // pull in write(str) and write(buf, size) from Print
    operator bool() { return true; }
};

extern LinuxConsole Console;

#endif
