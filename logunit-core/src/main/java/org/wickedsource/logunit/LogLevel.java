/**
 *   Copyright 2013 Tom Hombergs (tom.hombergs@gmail.com)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package org.wickedsource.logunit;

/**
 * Log levels supported by {@link LogUnit}. Depending on the used logging
 * framework, the logging frameworks levels have to be translated into LogUnit
 * log levels.
 * 
 * @author Tom Hombergs <tom.hombergs@gmail.com>
 * 
 */
public enum LogLevel {

	TRACE,

	DEBUG,

	INFO,

	WARN,

	ERROR,

	FATAL;

}
